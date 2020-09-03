window.Parsley.addValidator('rfc3986', {
  validateString: function(value) {
    return value && value.match(/^[A-Za-z0-9\s._~:\\/?#\[\]@!$&'()*+,;=-]*$/g) || false
  },
  messages: {
    en: 'This is not a valid string'
  }
});