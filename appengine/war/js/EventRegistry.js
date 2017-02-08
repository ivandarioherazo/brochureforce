/* global Class */

EventRegistry = Class.create();

/*
 * Returns a valid jQuery selector for DOM objects. Needed for JSF controls.
 * 
 * @param {type} controlId Control ID.
 * @returns {object} jQuery selector object.
 */
function $$(controlId) {
  return $(controlId.indexOf(":") > 0 ? "[id='" + controlId + "']" : "#" + controlId);
}

/**
 * Registers click event on a DOM control.
 * @param {string} controlId DOM control ID in which the click event is going to be registered on.
 * @param {function} eventFunction Function which is fired when click event occurs.
 */
EventRegistry.registerClickEvent = function(controlId, eventFunction) {
  $$(controlId).on("click", eventFunction);
};

/**
 * Registers change event on a DOM control.
 * @param {string} controlId DOM control ID in which the click event is going to be registered on.
 * @param {function} eventFunction Function which is fired when click event occurs.
 */
EventRegistry.registerChangeEvent = function(controlId, eventFunction) {
  $$(controlId).on("change", eventFunction);
};

/**
 * Registers change event on a DOM control.
 * @param {string} controlId DOM control ID in which the click event is going to be registered on.
 * @param {function} eventFunction Function which is fired when click event occurs.
 */
EventRegistry.registerMouseOverEvent = function(controlId, eventFunction) {
  $$(controlId).on("mouseover", eventFunction);
};

/**
 * Registers change event on a DOM control.
 * @param {string} controlId DOM control ID in which the click event is going to be registered on.
 * @param {function} eventFunction Function which is fired when click event occurs.
 */
EventRegistry.registerMouseOutEvent = function(controlId, eventFunction) {
  $$(controlId).on("mouseout", eventFunction);
};
