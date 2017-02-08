(function () {
  var initializing = false, fnTest = /xyz/.test(function () {
    xyz;
  }) ? /\b_super\b/ : /.*/;

  // The base Class implementation (does nothing)
  this.Class = function () {};
  
  function FinalClassInheritanceException(prop) {
    alert("ERROR: You have tried to extend a non-extensible class.");
    return null;
  };

  // Create a new Class that inherits from this class
  Class.create = function (prop) {
    // Use an empty object if no properties have been passed along to create the class.
    if( !prop ) {
      prop = {};
    }
    var _super = this.prototype;

    // Instantiate a base class (but only create the instance,
    // don’t run the "construct" function)
    initializing = true;
    var prototype = new this();
    initializing = false;
    
    ////////////////////////////////////////////////////////////////////////////
    // CODE TO DISABLE INHERITANCE MECHANISM.
    ////////////////////////////////////////////////////////////////////////////
    // Check property "extensible", which specifies if the inheritance
    // mechanism has been disabled for this class.
    var isNotExtensible = prop.extensible !== undefined && prop.extensible === false;
    if ( prop.extensible !== undefined ) {
      // Delete "extensible" property from the object used to extend the class
      // since it's going to be defined as read-only property.
      delete prop.extensible;
    }
    Object.defineProperty(prop, "extensible", {
      value: !isNotExtensible,
      writable: false,
      enumerable: true
    });
    // Prepare the inheritance behavior depending on the "extensible" property.
    var extendsWith = prop.extensible ? arguments.callee : FinalClassInheritanceException;
    ////////////////////////////////////////////////////////////////////////////

    // Copy the properties over onto the new prototype
    for (var name in prop) {

      // Check if we’re overwriting an existing function
      prototype[name] = typeof prop[name] === "function" &&
      typeof _super[name] === "function" && fnTest.test(prop[name]) ?
      (function (name, fn) {
        return function () {
          var tmp = this._super;

          // Add a new ._super() method that is the same method
          // but on the super-class
          this._super = _super[name];

          // The method only need to be bound temporarily, so we
          // remove it when we’re done executing
          var ret = fn.apply(this, arguments);
          this._super = tmp;

          return ret;
        };
      })(name, prop[name]) :
      prop[name];
    }

    // The dummy class constructor
    function NewClass() {
      // All construction is actually done in the "$constructor$" method.
      if (!initializing && this.$constructor$) {
        this.$constructor$.apply(this, arguments);
      }
    }

    // Populate our constructed prototype object
    NewClass.prototype = prototype;

    // Enforce the constructor to be what we expect
    NewClass.prototype.constructor = NewClass;

    // Set the inheritance behavior.
    NewClass.extendsWith = extendsWith;
    
    return NewClass;
  };
})();

/*
 * CLASS DEFINITIONS ON ECMA 6.
class Shape {
    constructor (id, x, y) {
        this.id = id
        this.move(x, y)
    }
    move (x, y) {
        this.x = x
        this.y = y
    }
}
*/