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