function warning() {
    var redirect = confirm("* * * * * * * * * * W A R N I N G * * * * * * * * * *\n" +
            "This computer system is the property of the University of Maryland Global " +
            "Campus (UMGC). It is for authorized use only.  By using this system, all users " +
            "acknowledge notice of, and agree to comply with, UMGC's Acceptable Use Policy " +
            "(\"AUP\"). Unauthorized or improper use of this system may result in " +
            "administrative disciplinary action, civil charges/criminal penalties, and/or " +
            "other sanctions as set forth in UMGC's AUP. By continuing to use this system " +
            "you indicate your awareness of and consent to these terms and conditions of " +
            "use.\n" +
            "If you are physically located in the European Union, you may have additional " +
            "rights per the GDPR. Visit the web site dataprivacy.utk.edu for more " +
            "information.\n" +
            "LEAVE IMMEDIATELY if you do not agree to the conditions stated in this warning.\n" +
            "* * * * * * * * * * W A R N I N G * * * * * * * * * *");
    if (redirect === false) {
        window.location.href = 'https://www.umgc.edu/administration/policies-and-reporting/policies/fiscal-and-business-affairs/acceptable-use.cfm';
        // document.location = 'index';
    }
}

