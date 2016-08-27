
storageClasses = {
    Html : 'storageHtml',
    Src : 'storageSrc',
    Value : 'storageVal'
};

function setHtmlItemsFromStorage() {
    for (var key in storageClasses) {
        if (storageClasses.hasOwnProperty(key)) {
            var container = $('.'+storageClasses[key]);
            console.log(container);
            container.each(function(){
                console.log();
                var storageIndex = $(this).data('storage');
                var data = localStorage.getItem(storageIndex);
                console.log(storageIndex, data);
                if(data !== null) {
                    var method = 'set'+key;
                    window[method]($(this), data);
             //       method($(this), data);
                }
            });
        }
    }
}

function setHtml() {

}

function setSrc(container,data) {
    console.log('setSrc');
    container.attr('src', data);
}

function setValue(container,data) {
    container.attr('value', data);
}

$(document).ready(function(){
    setHtmlItemsFromStorage();
});