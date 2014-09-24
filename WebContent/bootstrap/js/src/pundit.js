$.validator.addMethod('completeUrl', function(value, element, param) {
  if (value.length == 0) { return true; }

  if(!/^(https?|ftp):\/\//i.test(value)) {
    if(("http://").substring(1, value.length) != value.substring(1, value.length) && ("https://").substring(1, value.length) != value.substring(1, value.length)){
      value = 'http://' + value;
    }$(element).val(value);
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

$.validator.addMethod('requireMemberNum', function(value, element, param) {
  if (!_.str.contains(param[0].value, 'Not a member')) {
    return (value.length > 0);
  }
  return true; 
}, 'You need to enter your membership number.');

var util = {
  getPhoto: function(photo) {
    if (_.isNull(photo)) {
      photo = '';
    } else if (!_.str.startsWith(photo, 'http')) {
      var serverUrl = $('input[name="serverUrl"]').val();
      photo = serverUrl.concat(photo);
    }
    
    return photo;
  },
  
  getParam: function(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
        results = regex.exec(location.search);
    return results == null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
  },
  
  formatDate: function(input) {
    var pattern = /(.*?)\/(.*?)\/(.*?)$/;
    var result = input.replace(pattern,function(match, p1, p2, p3) {
      var months = ['January','February','March','April','May','June','July','August','September','October','November','Dececember'];
      return months[(p1 - 1)] + " " + (p2<10?+p2:p2) + ", " + p3;
    });
    return result;
  },
  
  formatCurrency : function(n, currency) {
    return currency + " " + n.toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, "$1,");
  }
};