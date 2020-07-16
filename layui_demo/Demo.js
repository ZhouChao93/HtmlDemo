/**
 *
 * @param div_id
 * @param data
 * @param name
 * @param value
 * @param checkboxName
 * @param append
 */
function checkbox_done(div_id, data, name, value,checkboxName,append) {
    if('false'===append){
        $('#' + div_id).empty();
    }
    $.each(data, function (index, item) {
        $("#"+div_id).append("<input type='checkbox' title=" + item[name] + " name="+checkboxName+" value=" + item[value] + "  />");
    });
    form.render();
}