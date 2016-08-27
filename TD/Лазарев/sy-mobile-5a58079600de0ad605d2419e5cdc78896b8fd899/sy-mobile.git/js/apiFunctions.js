/**
 * Created by yura on 16.04.16.
 */

function login(loginData) {
    var data;
    if(!loginData) {
        var email = $("[name=email]").val();
        var password = $("[name=password]").val();
        data = {email: email, password: password};
    }
    else {
        data = loginData;
    }
    send('login', data, function (remoteData) {
        localStorage.setItem('auth', 'true');
        try {
            if (remoteData.success == '1') {
                localStorage.setItem('userName', remoteData.data.user_name);
                localStorage.setItem('userId', remoteData.data.user_id);
                localStorage.setItem('userAvatar', remoteData.data.user_avatar);
                $('.user-btn img').attr('src', remoteData.data.user_avatar);
                $('.title').text(remoteData.info);
                $('.login-page').hide();
                $('#main-page').show();
                return true;
            } else {
                $('.title').text(remoteData.info);
                return false;
            }
        }
        catch(e) { pushConsole(e, arguments.callee);  return false; }
    });
}

function logout() {
    localStorage.setItem('auth', 'false');
    send('logout', {}, function (remoteData) {
        try {
            if (remoteData.success == '1') {
                $('.title').text(remoteData.info);
                $('.login-page').show();
                $('#main-page').hide();
            } else {
                $('.title').text(remoteData.info);
            }
        }
        catch(e) { pushConsole(e, arguments.callee); }
    });
}

function register(data, email, pass) {
    send('register', data, function (remoteData) {
        try {
            if (remoteData.success == '1') {
                login({email: email, password: pass});
                    window.location.href = '/';
            } else {
                pushMessage(MSG_REGISTRATION, remoteData.info);
            }
        }
        catch(e) { pushConsole(e, arguments.callee); }
    });
}

function addVideoLike(videoId, element) {
    send('addVideoLike', {videoId: videoId}, function (remoteData) {
        try {
            var newLikeVal;
            switch (remoteData.action_code) {
                case 1:
                {
                    var newDisLikeVal = parseInt(element.parent().find('.btn-dislike span').html()) - 1;
                    element.parent().find('.btn-dislike span').html(newDisLikeVal);
                    newLikeVal = parseInt(element.find('span').html()) + 1;
                    element.find('span').html(newLikeVal);
                    break;
                }
                case 2:
                {
                    newLikeVal = parseInt(element.find('span').html()) + 1;
                    element.find('span').html(newLikeVal);
                    break;
                }
                default :
                {
                    break;
                }
            }
        }
        catch(e) { pushConsole(e, arguments.callee); }
    });
}

function addVideoDislike(videoId, element) {
    send('addVideoDislike', {videoId: videoId}, function (remoteData) {
        try {
            var newDisLikeVal;
            switch (remoteData.action_code) {
                case 1:
                {
                    var newLikeVal = parseInt(element.parent().find('.btn-like span').html()) - 1;
                    element.parent().find('.btn-like span').html(newLikeVal);
                    newDisLikeVal = parseInt(element.find('span').html()) + 1;
                    element.find('span').html(newDisLikeVal);
                    break;
                }
                case 2:
                {
                    newDisLikeVal = parseInt(element.find('span').html()) + 1;
                    element.find('span').html(newDisLikeVal);
                    break;
                }
                default :
                {
                    break;
                }
            }
        }
        catch(e) { pushConsole(e, arguments.callee); }
    });
}

function getCountries() {
    if(localStorage.getItem('countryItems') && localStorage.getItem('cityItems_1')) {
        setCountryCitySelect();
    }
    else {
        send('getCountries', {}, function (remoteData) {
            try {
                var countryItems = '';
                for (var i = 0; i < remoteData.data.length; i++) {
                    countryItems += '<option value="'+remoteData.data[i].id+'">'+remoteData.data[i].name+'</option>';
                    getCities(remoteData.data[i].id);
                }
                localStorage.setItem('countryItems', countryItems);

                setTimeout(function(){
                    setCountryCitySelect();
                }, 2000);
            }
            catch(e) {
                pushConsole(e, arguments.callee);
            }
        });
    }
}

function getCities(countryId) {
    send('getCities', {'countryId':countryId}, function (remoteData) {
        try {
            var cityItems = '';
            for (var i = 0; i < remoteData.data.length; i++) {
                cityItems += '<option value="'+remoteData.data[i].id+'">'+remoteData.data[i].name+'</option>';
            }
            localStorage.setItem('cityItems_'+countryId, cityItems);
        }
        catch(e) {
            pushConsole(e, arguments.callee);
        }
    });
}

function setCountryCitySelect() {

    var countryOptions = localStorage.getItem('countryItems');
    var cityOptions = localStorage.getItem('cityItems_1');

    try {
        app.container.countrySelect.append(countryOptions);
        app.container.citySelect.append(cityOptions);
    }
    catch(e) {
        pushConsole(e, arguments.callee);
    }
}

function setVideoCategoriesSelect() {

    var videoCategories = localStorage.getItem('videoCategories');

    try {
        app.container.videoCategoriesSelect.append(videoCategories);
    }
    catch(e) {
        pushConsole(e, arguments.callee);
    }
}

function getVideoCategories() {

    if(localStorage.getItem('videoCategories')) {
        setVideoCategoriesSelect();
    }
    else {
        send('getVideoCategories', {}, function (remoteData) {
            try {
                var videoCategories = '';
                for (var i = 0; i < remoteData.data.length; i++) {
                    videoCategories += '<option value="'+remoteData.data[i].id+'">'+remoteData.data[i].name+'</option>';
                }
                localStorage.setItem('videoCategories', videoCategories);

                setTimeout(function(){
                    setVideoCategoriesSelect();
                }, 2000);
            }
            catch(e) {
                pushConsole(e, arguments.callee);
            }
        });
    }
}















// for disable push console messages in prod in one point of code
function pushConsole(e, sender) {
    console.log(e, sender);
}