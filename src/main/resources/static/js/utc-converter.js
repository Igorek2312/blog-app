$('.utc-date-time').each(function() {
    var dateTime = $(this).text();

    var stillUtc = moment.utc(dateTime).toDate();
    var local = moment(stillUtc).local().format('YYYY-MM-DD HH:mm:ss');

    $(this).text(local);
});