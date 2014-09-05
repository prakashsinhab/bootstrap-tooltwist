$.validator.addMethod('completeUrl', function(value, element, param) {
  if (value.length == 0) { return true; }

  if(!/^(https?|ftp):\/\//i.test(value)) {
    value = 'http://' + value;
    $(element).val(value);
  }
  return /^(http:\/\/www\.|https:\/\/www\.|http:\/\/|https:\/\/)[a-z0-9]+([\-\.]{1}[a-z0-9]+)*\.[a-z]{2,5}(:[0-9]{1,5})?(\/.*)?$/.test(value);
}, 'Please enter a valid URL.');

$.validator.addMethod('greaterDate', function(value, element, param) {
  var isValid = false;
  var end = new Date(value);
  var start = new Date(param);
  if (_.isDate(start) && _.isDate(end)) {
    isValid = start <= end;
  }
  return isValid; 
}, 'Must be greater than {0} date.');

$.validator.addMethod('greaterInt', function(value, element, param) {
  return (_.str.toNumber(param) <= _.str.toNumber(value)); 
}, 'Must be greater than {0} value.');

var util = {
  getProfilePhoto: function(profilePhoto) {
    if (_.isNull(profilePhoto)) {
      profilePhoto = '';
    } else if (!_.str.startsWith(profilePhoto, 'http')) {
      var serverUrl = $('input[name="serverUrl"]').val();
      profilePhoto = serverUrl + profilePhoto;
    }
    
    return profilePhoto;
  }
};