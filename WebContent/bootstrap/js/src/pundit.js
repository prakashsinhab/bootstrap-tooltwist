$.validator.addMethod('completeUrl', function(value, element, param) {
  if (value.length == 0) { return true; }

  if(!/^(https?|ftp):\/\//i.test(value)) {
    value = 'http://' + value;
    $(element).val(value);
  }
  return /^(http:\/\/www\.|https:\/\/www\.|http:\/\/|https:\/\/)[a-z0-9]+([\-\.]{1}[a-z0-9]+)*\.[a-z]{2,5}(:[0-9]{1,5})?(\/.*)?$/.test(value);
}, 'Please enter a valid URL.');

var util = {
  getProfilePhoto: function(profilePhoto) {
    if (!_.str.startsWith(profilePhoto, 'http')) {
      var serverUrl = $('input[name="serverUrl"]').val();
      profilePhoto = serverUrl + profilePhoto;
    }
    
    return profilePhoto;
  }
};