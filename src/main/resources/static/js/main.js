$('#profile-image').change(function () {
    $('#profile-image-form').submit();
});

$('#attache-file').change(function () {
    $('#attache-file-form').submit();
});

function getParameterByName(name, url) {
    if (!url) url = window.location.href;
    name = name.replace(/[\[\]]/g, "\\$&");
    var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
        results = regex.exec(url);
    if (!results) return null;
    if (!results[2]) return '';
    return decodeURIComponent(results[2].replace(/\+/g, " "));
}

if (getParameterByName('login', location.href)) {
    $('#login-modal').modal('show');
}