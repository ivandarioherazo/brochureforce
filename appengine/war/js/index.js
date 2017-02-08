/* global EventRegistry, $$ */

var LOGIN_FORM_ID = "loginForm";
$(document).ready(function () {
  var userFields = [":profileType", ":companyId", ":userId", ":userPwd"];
  var placeholders = ["Company ID", "User ID", "Password"];
  function userField(idx) {
    return LOGIN_FORM_ID + userFields[idx];
  }
  var profileType = userField(0);
  var companyId = userField(1);
  var userId = userField(2);
  var userPwd = userField(3);
  
  EventRegistry.registerChangeEvent(profileType, function () {
    if ($$(profileType).val() === "COMPANY") {
      $$(companyId).css("display", "block");
      $$(userId).css("display", "none");
    } else if( $$(profileType).val() === "SELLER_COMPANY" ) {
      $$(companyId).css("display", "block");
      $$(userId).css("display", "block");
    } else { // if( $("#profileType").val() === "CLIENT" )
      $$(companyId).css("display", "none");
      $$(userId).css("display", "block");
    }
  });
  
  $$(profileType).attr("mini", "true");
  
  var companyIdPlh = placeholders[0];
  var userIdPlh = placeholders[1];
  var userPwdPlh = placeholders[2];
  
  $$(companyId).attr('placeholder', companyIdPlh);
  $$(userId).attr('placeholder', userIdPlh);
  $$(userPwd).attr('placeholder', userPwdPlh);
  
  EventRegistry.registerMouseOverEvent(companyId, function () {
    $$(companyId).attr('placeholder', '');
  });
  EventRegistry.registerMouseOutEvent(companyId, function () {
    $$(companyId).attr('placeholder', companyIdPlh);
  });

  EventRegistry.registerMouseOverEvent(userId, function () {
    $$(userId).attr('placeholder', '');
  });
  EventRegistry.registerMouseOutEvent(userId, function () {
    $$(userId).attr('placeholder', userIdPlh);
  });

  EventRegistry.registerMouseOverEvent(userPwd, function () {
    $$(userPwd).attr('placeholder', '');
  });
  EventRegistry.registerMouseOutEvent(userPwd, function () {
    $$(userPwd).attr('placeholder', userPwdPlh);
  });
});
