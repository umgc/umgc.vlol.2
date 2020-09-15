window.Parsley.addValidator('rfc3986', {
  validateString: function(value) {
    return value && value.match(/^[A-Za-z0-9\s._~:\\/?#\[\]@!$&'()*+,;=-]*$/g) || false
  },
  messages: {
    en: 'Input contains illegal characters'
  }
});

window.Parsley.addValidator('social', {
  validateMultiple: function(value) {
    return value && value.match(/^\d{3}-?\d{2}-?\d{4}$/g) || false
  },
  messages: {
    en: 'Invalid input'
  }
});

window.Parsley.addValidator('dob', {
  validateMultiple: function(value) {
    return value && value.match(/(0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])[- /.](19|20)\d\d/) || false
  },
  messages: {
    en: 'Input contains illegal characters'
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

