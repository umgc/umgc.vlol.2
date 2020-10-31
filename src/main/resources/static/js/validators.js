window.Parsley.options.excluded = "input[type=button], input[type=submit], input[type=reset], input[type=hidden], [disabled], :hidden, [readonly]";

window.Parsley.addValidator('rfc3986', {
  validateString: function(value) {
    return value && value.match(/^[A-Za-z0-9\s._~:\\/?#\[\]@!$&'()*+,;=-]*$/g) || false
  },
  messages: {
    en: 'Input contains illegal characters'
  }
});

window.Parsley.addValidator('social', {
  validateString: function(value) {
    return value && value.match(/^\d{9}$/) || false
  },
  messages: {
    en: 'Invalid ssn'
  }
});

window.Parsley.addValidator('phone', {
  validateString: function(value) {
   return value && value.match(/^\(?(\d{3})\)?[- ]?(\d{3})[- ]?(\d{4})$/) || false
  },
  messages: {
    en: 'Invalid phone number'
  }
});

// Usage for 2mb max size: data-parsley-max-file-size-mb="2" 
window.Parsley.addValidator('maxFileSizeMb', {
  validateString: function(_value, maxSize, parsleyInstance) {
    var files = parsleyInstance.$element[0].files;
    return files.length !== 1  || files[0].size <= maxSize * 1024 * 1024;
  },
  requirementType: 'integer',
  messages: {
    en: 'Input must be less then %s Mb.'
  }
});

window.Parsley.addValidator('email', {
  validateString: function(value) {
    return value && value.match(/^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/) || false
  },
  messages: {
    en: 'Invalid email'
  }
});

window.Parsley.addValidator('npi', {
  validateString: function (value) {
        value = value.replace(/[ -]/g, '');
    let digit;
    let n;
    let _j;
    let _len1;
    let _ref2;
    let sum = 24;
    _ref2 = value.split('');
    const checkDigit = parseInt(_ref2[_ref2.length-1]);
    for (n = _j = 0, _len1 = _ref2.length-1; _j < _len1; n = ++_j) {
      digit = _ref2[n];
      digit = +digit;
      if (n % 2 === 0) {
        digit *= 2;
        if (digit < 10) {
          sum += digit;
        } else {
          sum += digit - 9;
        }
      } else {
        sum += digit;
      }
    }
    return (10-(sum % 10))%10 === checkDigit;
  },
  messages: {
    en: 'Invalid NPI'
  }
});
