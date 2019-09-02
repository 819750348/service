var Common = {};
Common.hasClass = function (elements, cName) {
    return !!elements.className.match(
        new RegExp("(\\s|^)" + cName + "(\\s|$)")
    );
};
Common.addClass = function (elements, cName) {
    if (!Common.hasClass(elements, cName)) {
        elements.className += " " + cName;
    }
};
Common.removeClass = function (elements, cName) {
    if (Common.hasClass(elements, cName)) {
        elements.className = elements.className.replace(
            new RegExp("(\\s|^)" + cName + "(\\s|$)"),
            " "
        );
    }
};
Common.removeAllClass = function (elements) {
    elements.className = "";
};
Common.formElementUpload = function () {
    var buttonUploads = document.querySelectorAll(".el-button-upload");
    buttonUploads.forEach(item => {
        Common.removeAllClass(item.children[0]);
    });
};
export default Common;
