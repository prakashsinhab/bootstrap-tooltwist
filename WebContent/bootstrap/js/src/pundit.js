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

var cropbox = function(options){
    var el = document.querySelector(options.imageBox),
    obj =
    {
        state : {},
        ratio : 1,
        options : options,
        imageBox : el,
        thumbBox : el.querySelector(options.thumbBox),
        spinner : el.querySelector(options.spinner),
        image : new Image(),
        getAvatar: function ()
        {
            var width = this.thumbBox.clientWidth,
                height = this.thumbBox.clientHeight,
                canvas = document.createElement("canvas"),
                dim = el.style.backgroundPosition.split(' '),
                size = el.style.backgroundSize.split(' '),
                dx = parseInt(dim[0]) - el.clientWidth/2 + width/2,
                dy = parseInt(dim[1]) - el.clientHeight/2 + height/2,
                dw = parseInt(size[0]);
            dh = parseInt(size[1]);
            sh = parseInt(this.image.height);
            sw = parseInt(this.image.width);

            canvas.width = width;
            canvas.height = height;
            var context = canvas.getContext("2d");
            context.drawImage(this.image, 0, 0, sw, sh, dx, dy, dw, dh);
            var imageData = canvas.toDataURL('image/jpeg');
            return imageData;
        },
        zoomIn: function ()
        {
            this.ratio*=1.1;
            setBackground();
        },
        zoomOut: function ()
        {
            this.ratio*=0.9;
            setBackground();
        }
    },
    setBackground = function()
    {
        var w =  parseInt(obj.image.width)*obj.ratio;
        var h =  parseInt(obj.image.height)*obj.ratio;

        var pw = (el.clientWidth - w) / 2;
        var ph = (el.clientHeight - h) / 2;

        el.setAttribute('style',
                'background-image: url(' + obj.image.src + '); ' +
                'background-size: ' + w +'px ' + h + 'px; ' +
                'background-position: ' + pw + 'px ' + ph + 'px; ' +
                'background-repeat: no-repeat');
    },
    imgMouseDown = function(e)
    {
        e.stopImmediatePropagation();

        obj.state.dragable = true;
        obj.state.mouseX = e.clientX;
        obj.state.mouseY = e.clientY;
    },
    imgMouseMove = function(e)
    {
        e.stopImmediatePropagation();

        if (obj.state.dragable)
        {
            var x = e.clientX - obj.state.mouseX;
            var y = e.clientY - obj.state.mouseY;

            var bg = el.style.backgroundPosition.split(' ');

            var bgX = x + parseInt(bg[0]);
            var bgY = y + parseInt(bg[1]);

            el.style.backgroundPosition = bgX +'px ' + bgY + 'px';

            obj.state.mouseX = e.clientX;
            obj.state.mouseY = e.clientY;
        }
    },
    imgMouseUp = function(e)
    {
        e.stopImmediatePropagation();
        obj.state.dragable = false;
    },
    zoomImage = function(e)
    {
        var evt=window.event || e;
        var delta=evt.detail? evt.detail*(-120) : evt.wheelDelta;
        delta > -120 ? obj.ratio*=1.1 : obj.ratio*=0.9;
        setBackground();
    },
    attachEvent = function(node, event, cb)
    {
        if (node.attachEvent)
            node.attachEvent('on'+event, cb)
        else if (node.addEventListener)
            node.addEventListener(event, cb)
    },
    detachEvent = function(node, event, cb)
    {
        if(node.detachEvent) {
            node.detachEvent('on'+event, cb)
        }
        else if(node.removeEventListener) {
            node.removeEventListener(event, render);
        }
    }

    obj.spinner.style.display = 'block';
    obj.image.onload = function() {
        obj.spinner.style.display = 'none';
        setBackground();

        attachEvent(el, 'mousedown', imgMouseDown);
        attachEvent(el, 'mousemove', imgMouseMove);
        attachEvent(window, 'mouseup', imgMouseUp);
        var mousewheel = (/Firefox/i.test(navigator.userAgent))? 'DOMMouseScroll' : 'mousewheel';
        attachEvent(el, mousewheel, zoomImage);
    };
    obj.image.src = options.imgSrc;
    attachEvent(el, 'DOMNodeRemoved', function(){detachEvent(window, 'DOMNodeRemoved', imgMouseUp)});

    return obj;
};


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
  
  getFile: function(file) {
    if (_.isNull(file)) {
      file = '';
    } else {
      var serverUrl = $('input[name="serverUrl"]').val();
      file = serverUrl.concat(file);
    }
    
    return file;
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
  },
  
  
  dataURItoBlob : function(dataURI) {
    // convert base64/URLEncoded data component to raw binary data held in a string
    var byteString;
    if (dataURI.split(',')[0].indexOf('base64') >= 0)
        byteString = atob(dataURI.split(',')[1]);
    else
        byteString = unescape(dataURI.split(',')[1]);
    // separate out the mime component
    var mimeString = dataURI.split(',')[0].split(':')[1].split(';')[0]
    // write the bytes of the string to a typed array
    var ia = new Uint8Array(byteString.length);
    for (var i = 0; i < byteString.length; i++) {
        ia[i] = byteString.charCodeAt(i);
    }
    return new Blob([ia], {type:mimeString});
  }
  
};