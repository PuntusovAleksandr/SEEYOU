host = "http://139.162.149.111/";
//host = "http://test.com/";
service = host + "api/";
app = {};

const MSG_REGISTRATION = '1';

function initContainer() {

    app.container = {
        citySelect : $('.citySelect'),
        videoCategoriesSelect : $('.videoCategoriesSelect'),
        countrySelect : $('.countrySelect'),
        register : $('.register'),
        birthDay : $('.birth-day'),
        birthMonth : $('.birth-month'),
        birthYear : $('.birth-year'),
        birthDate : $('.birthDate'),
        email : $('.email'),
        password : $('.password'),
        msgRegister : $('.msgRegister'),
        responseMsgContainer : $('.responseMsgContainer')
    };

    app.container.countrySelect.on('change', function(){
        var countryId = $(this).val();
        if(countryId != '') {
            changeCitySelect(countryId);
        }
    });

    app.container.register.on('submit', function(e) {
        e.preventDefault();
        var birthDay = app.container.birthDay.val();
        var birthYear = app.container.birthYear.val();
        var birthMonth = app.container.birthMonth.val();

        var email = app.container.email.val();
        var password = app.container.password.val();

        if(birthDay != '' && birthMonth != '' && birthYear != '') {
            app.container.birthDate.val(birthDay+'.'+birthMonth+'.'+birthYear);
        }

        var registerData = $(this).serialize();
        register(registerData, email, password);

        return false;
    });

}

function changeCitySelect(countryId){
    var cityOptions = localStorage.getItem('cityItems_'+countryId);
    cityOptions = '<option value="">Select city</option>'+cityOptions;
    try {
        app.container.citySelect.html(cityOptions);
    }
    catch(e) {
        pushConsole(e, arguments.callee);
    }
}

function pushMessage(type, data) {
    try {
        var msgContainer;
        switch(type) {
            case MSG_REGISTRATION : {
                msgContainer = app.container.msgRegister;
                break;
            }
            default : break;
        }
        msgContainer.html(data);
    }
    catch(e) {
        pushConsole(e, arguments.callee);
    }
}

function hideMessage() {
    try {
        app.container.responseMsgContainer.html('');
    }
    catch(e) {
        pushConsole(e, arguments.callee);
    }
}

$.fn.disable = function() {
    $(this).attr('disabled','disabled');
}

$.fn.enable = function() {
    $(this).removeAttr('disabled');
}


$(function () {
    console.log("ready!");

    if (localStorage.getItem('auth') == "true") {
        $('.login-page').hide();
        $('#main-page').show();
//        console.log('true');
    } else {
        $('.login-page').show();
        $('#main-page').hide();
//        console.log('false');
    }
});
//apiKey = "demo";

function check() {


}


$(":file").click(function () {
    console.log('click');

    $(this).ajaxfileupload({
        'action': sevrice + 'mupload',
        'params': {
            'id': 1
        },
        'onComplete': function () {
            console.log('custom handler for file:');

        },
        'onStart': function () {
            console.log('start');
            // cancels upload
        },
        'onCancel': function () {
            console.log('no file selected');

        }
    });

});


function send(url, data, val) { // ������� �������� ���� �������� � ���������������� � ����������� ������� ��� ������

    $.ajax({
        method: "POST",
        url: service + url,
        dataType: 'json',
        xhrFields: {
            withCredentials: true
        },
        crossDomain: true,
        data: data
    })
        .done(function (remoteData) {
            val(remoteData)
        });
}

var statemant;

function getSlide(id, link) {
    if (link == 0) {
        $('#video-page-videoselfie-container').hide();
        $('#home-videoselfie-container').show();
    }
    if (link == 1) {
        $('#broadcast-video-container').hide();
        $('#broadcast-home-container').show();
        $('.video-player-box-broad').html('<div></div>');
    }

    if (id !== statemant) {
        if (id == 0) {
            $('.video-player-box-broad').html('<div></div>');
            $('#broadcast-video-container').hide();
            $('#broadcast-home-container').show();
            $(".broadcast-video-container video").each(function () {
                $(this).get(0).pause();
            });
            statemant = 0;
            getVideoCategories();
            getPopularVideoList('', 5);
            getLastVideoList('', 5);
            getVideoList('', 5);
        }

        if (id == 1) {
            $(".video-page-videoselfie-container video").each(function () {
                $(this).get(0).pause();
            });
            statemant = 1;
            getBroadcasts('', 15);
            getBroadcastCategories();
        }

        if (id == 2) {
            statemant = 2;
        }
    }
}

var ist = 10;
$('#loading-btn').click(function () {
    console.log(ist);
    ist = load(ist)
});

function load(i) {
    getPopularVideoList('', i);
    getLastVideoList('', i);
    getVideoList('', i);
    return i + 5;
}


function getVideoList(category, limit) { // ��������� ������ ����� (��������� � ����� �� ������������)

    send('getVideoList', {category: category, limit: limit}, function (remoteData) {

        var videoItem = '';

        for (var i = 0; i < remoteData.data.length; i++) {

            videoItem += '<div class="video-item">\
              <a href="#video/id=' + remoteData.data[i].id + '" onclick="getVideoFileById(' + remoteData.data[i].id + ');" class="video-link">\
                <div class="video-item-preview">\
                  <img src="' + host + remoteData.data[i].video_src + '.jpg">\
                </div>\
                <div class="video-item-about">\
                  <div class="video-item-title" title="' + remoteData.data[i].title + '">\
                   ' + remoteData.data[i].title + '\
                  </div>\
                  <div class="video-item-addition">\
                    by <b>' + remoteData.data[i].author_name + '</b>\
                    <br>\
                    Views <b>' + remoteData.data[i].views + '</b>\
                  </div>\
                </div>\
              </a>\
            </div>';

        }


        $('#seeyouchoice').html('<h3>SeeYou Choice</h3>' + videoItem);
    });
}

function getPopularVideoList(category, limit) {

    send('getPopularVideoList', {category: category, limit: limit}, function (remoteData) {

        var videoItem = '';

        for (var i = 0; i < remoteData.data.length; i++) {
            // if (remoteData.data[i].id != id ){
            videoItem += '<div class="video-item">\
              <a href="#video/id=' + remoteData.data[i].id + '" onclick="getVideoFileById(' + remoteData.data[i].id + ');" class="video-link">\
                <div class="video-item-preview">\
                  <img src="' + host + remoteData.data[i].video_src + '.jpg">\
                </div>\
                <div class="video-item-about">\
                  <div class="video-item-title" title="' + remoteData.data[i].title + '">\
                   ' + remoteData.data[i].title + '\
                  </div>\
                  <div class="video-item-addition">\
                    by <b>' + remoteData.data[i].author_name + '</b>\
                    <br>\
                    Views <b>' + remoteData.data[i].views + '</b>\
                  </div>\
                </div>\
              </a>\
            </div>';
            //}
        }

        $('#popular').html('<h3>Popular</h3>' + videoItem);
    });
}

function getLastVideoList(category, limit) {

    send('getLastVideoList', {category: category, limit: limit}, function (remoteData) {
        var videoItem = '';

        for (var i = 0; i < remoteData.data.length; i++) {
            // if (remoteData.data[i].id != id ){
            videoItem += '<div class="video-item">\
              <a href="#video/id=' + remoteData.data[i].id + '" onclick="getVideoFileById(' + remoteData.data[i].id + ');" class="video-link">\
                <div class="video-item-preview">\
                  <img src="' + host + remoteData.data[i].video_src + '.jpg">\
                </div>\
                <div class="video-item-about">\
                  <div class="video-item-title" title="' + remoteData.data[i].title + '">\
                   ' + remoteData.data[i].title + '\
                  </div>\
                  <div class="video-item-addition">\
                    by <b>' + remoteData.data[i].author_name + '</b>\
                    <br>\
                    Views <b>' + remoteData.data[i].views + '</b>\
                  </div>\
                </div>\
              </a>\
            </div>';
            //}
        }


        $('#new').html('<h3>New</h3>' + videoItem);
    });
}

function getBroadcasts(category, limit) {

    $.getJSON(service + 'getBroadcasts', {category: category, limit: limit}, function (remoteData) {

        var videoItem = '';

        for (var i = 0; i < remoteData.data.length; i++) {
            var stream = 'http://139.162.144.131/cam1/';
            var streamVideo = "'" + remoteData.data[i].src + "'";
            // if (remoteData.data[i].id != id ){
            videoItem += '<div class="video-item">\
              <a href="#video/id=' + remoteData.data[i].id + '" onclick="getVideoFileByIdBroadcast(' + streamVideo + ');" class="video-link">\
                <div class="video-item-preview">\
                  <img src="' + stream + 'preview.jpg">\
                </div>\
                <div class="video-item-about">\
                  <div class="video-item-title" title="' + remoteData.data[i].title + '">\
                   ' + remoteData.data[i].title + '\
                  </div>\
                  <div class="video-item-addition">\
                    by <b>' + remoteData.data[i].name + '</b>\
                    <br>\
                    Views <b>' + remoteData.data[i].views + '</b>\
                  </div>\
                </div>\
              </a>\
            </div>';
            //}
        }


        $('#broadcast').html('<h3>Broadcast</h3>' + videoItem);
    });
}

function getVideoComments(videoId) {
    var commentsItem = '';

    $.getJSON(service + 'getVideoComments', {videoId: videoId}, function (remoteData) {
        console.log(remoteData);

        try {
            for (var i = 0; i < remoteData.data.length; i++) {
                if (remoteData.data[i].type == '1') {
                    commentsItem += '<div class="comments-item">\
            <div class="comments-user-icon">\
              <img src="' + host + remoteData.data[i].author_avatar + '">\
            </div>\
            <div class="comments-content">\
              <div class="comments-user-name">\
                <a href="#">' + remoteData.data[i].author_name + '</a> <span class="t-date">' + moment(remoteData.data[i].created_at, "YYYY-MM-dd hh:mm:ss").fromNow() + '</span>\
              </div>\
              <div class="comments-text">\
               ' + remoteData.data[i].text + '\
              </div>\
              <div class="comments-replay-btn">\
                <a href="#" data-relation="' + remoteData.data[i].relation + '" data-type="1" data-resource="' + videoId + '">Replay</a>\
            </div>\
            </div>\
          </div>';
                } else {
                    commentsItem += '<div class="comments-sub-list"><div class="comments-item">\
            <div class="comments-user-icon">\
              <img src="' + host + remoteData.data[i].author_avatar + '">\
            </div>\
            <div class="comments-content">\
              <div class="comments-user-name">\
                <a href="#" data-relation="' + remoteData.data[i].relation + '" data-type="1" data-resource="' + videoId + '">Replay</a>\
            </div>\
              <div class="comments-text">\
               ' + remoteData.data[i].text + '\
              </div>\
              <div class="comments-replay-btn">\
               <a href="#" data-relation="' + remoteData.data[i].relation + '" data-type="1" data-id="' + videoId + '">Replay</a>\
             </div>\
            </div>\
          </div></div>';
                }
            }
        }
        catch (e) {
            //  console.log('An error has occurred: '+e.message)
        }


        $('.comments-main').html(commentsItem);

        $('.comments-replay-btn a').on('click', function (e) {
            e.preventDefault();
            console.log('caws');
            var el = $(this);
            var entity = el.data('type');
            var relation = el.data('relation');
            var resource = el.data('resource');
            var type = 2;
            el.parent().html('<div class="comments-content">\
                                    <textarea id="' + relation + '" class="comment-textarea replysing" placeholder="Write your comment here.."></textarea>\
                                    <div class="comments-textarea-controls" onclick="addComment(' + type + ',' + entity + ',' + resource + ',' + relation + ');">\
                                        <button type="button" onclick="addComment(' + type + ',' + entity + ',' + resource + ',' + relation + ');" class="comments-send-button">\
                                            Post\
                                        </button>\
                                    </div>\
                                </div>');
        });

    });

}

function getBroadcastComments(broadcastId) {
    var commentsItem = '';

    $.getJSON(service + 'getBroadcastComments', {broadcastId: broadcastId}, function (remoteData) {

        for (var i = 0; i < remoteData.data.length; i++) {
            if (remoteData.data[i].type == '1') {
                commentsItem += '<div class="comments-item">\
            <div class="comments-user-icon">\
              <img src="' + remoteData.data[i].user_avatar + '">\
            </div>\
            <div class="comments-content">\
              <div class="comments-user-name">\
                <a href="#">' + remoteData.data[i].user_name + '</a> <span class="t-date">' + moment(remoteData.data[i].created_at, "YYYY-MM-dd hh:mm:ss").fromNow() + '</span>\
              </div>\
              <div class="comments-text">\
               ' + remoteData.data[i].text + '\
              </div>\
              <div class="comments-replay-btn">\
                <a href="#" data-relation="' + remoteData.data[i].relation + '" data-type="2" data-id="' + broadcastId + '">Replay</a>\
              </div>\
            </div>\
          </div>';
            } else {
                commentsItem += '<div class="comments-sub-list"><div class="comments-item">\
            <div class="comments-user-icon">\
              <img src="' + remoteData.data[i].user_avatar + '">\
            </div>\
            <div class="comments-content">\
              <div class="comments-user-name">\
                <a href="#">' + remoteData.data[i].user_name + '</a> <span class="t-date">' + moment(remoteData.data[i].created_at, "YYYY-MM-dd hh:mm:ss").fromNow() + '</span>\
              </div>\
              <div class="comments-text">\
               ' + remoteData.data[i].text + '\
              </div>\
              <div class="comments-replay-btn">\
               <a href="#" data-relation="' + remoteData.data[i].relation + '" data-type="2" data-resource="' + broadcastId + '">Replay</a>\
             </div>\
            </div>\
          </div></div>';
            }
        }


        $('.comments-main').html(commentsItem);
    });

}
/*
function getVideoCategories() {

    $.getJSON(service + 'getVideoCategories', function (remoteData) {
        //console.log(remoteData);
        var html = '';

        for (var i = 0; i < remoteData.data.length; i++) {
            // console.log(remoteData.categories[i].id);
            html += '<li>\
                <a href="#categories/id=' + remoteData.data[i].id + '" onclick="getVideoFilesByCategory(\'' + remoteData.data[i].slug + '\')" class="nav-item">' + remoteData.data[i].name + '</a>\
              </li>';
        }


        $('.category').html(html);

    });

}*/

function getBroadcastCategories() {

    $.getJSON(service + 'getBroadcastCategories', function (remoteData) {
        //console.log(remoteData);
        var html = '';

        for (var i = 0; i < remoteData.data.length; i++) {
            // console.log(remoteData.categories[i].id);
            html += '<li>\
                <a href="#categories/id=' + remoteData.data[i].id + '" onclick="getVideoFilesByCategory(\'' + remoteData.data[i].slug + '\')" class="nav-item">' + remoteData.data[i].name + '</a>\
              </li>';
        }


        $('.category').html(html);

    });

}

function addComment(type, entity, resource, relation, e) {
    e.preventDefault();
    console.log('add');
    var commentsItem = '';
    var text = $('.replysing[id=' + relation + ']').val();
    var data = {
        text: text,
        type: type,
        entity: entity,
        resource: resource,
        relation: relation,
    };

    send('addComment', data, function (remoteData) {
        console.log(remoteData);
        getVideoComments('277');
        //$('.comments-main').append(remoteData.html);
    });


}

function getVideoSelfie() {
    $.getJSON(service + 'videoselfie', function (remoteData) {
        //console.log(remoteData);
        var html = '';

        for (var i = 0; i < remoteData.categories.length; i++) {
            // console.log(remoteData.categories[i].id);
            html += '<li>\
                <a href="#categories/id=' + remoteData.categories[i].id + '" onclick="getVideoFilesByCategory(\'' + remoteData.categories[i].slug + '\')" class="nav-item">' + remoteData.categories[i].name + '</a>\
              </li>';
        }


        $('.category').html(html);

        var videoItem = '';

        for (var i = 0; i < remoteData.new_video.length; i++) {
            videoItem += '<div class="video-item">\
              <a href="#video/id=' + remoteData.new_video[i].id + '" onclick="getIndex(\'' + remoteData.new_video[i].slug + '\', ' + remoteData.new_video[i].id + ')" class="video-link">\
                <div class="video-item-preview">\
                  <img src="' + host + remoteData.new_video[i].src + '.jpg">\
                </div>\
                <div class="video-item-about">\
                  <div class="video-item-title" title="' + remoteData.new_video[i].title + '">\
                   ' + remoteData.new_video[i].title + '\
                  </div>\
                  <div class="video-item-addition">\
                    by <b>' + remoteData.new_video[i].name + '</b>\
                    <br>\
                    Views <b>' + remoteData.new_video[i].views + '</b>\
                  </div>\
                </div>\
              </a>\
            </div>';
        }

        $('#list').hide();
        $('#play').show();
        $('.videoitem').html(videoItem);
        //console.log(html);
    });
}

function getVideoFilesByCategory(category, id) {
    $.getJSON(service + 'videofilesbycategory' + '/' + category, function (remoteData) {
        console.log(remoteData);
        var videoItem = '';
        for (var i = 0; i < remoteData.new_video.length; i++) {
            if (remoteData.new_video[i].id != id) {

                videoItem += '<div class="video-item">\
              <a href="#video/id=' + remoteData.new_video[i].id + '" onclick="getIndex(\'' + remoteData.new_video[i].slug + '\', ' + remoteData.new_video[i].id + ')" class="video-link">\
                <div class="video-item-preview">\
                  <img src="' + host + remoteData.new_video[i].src + '.jpg">\
                </div>\
                <div class="video-item-about">\
                  <div class="video-item-title" title="' + remoteData.new_video[i].title + '">\
                   ' + remoteData.new_video[i].title + '\
                  </div>\
                  <div class="video-item-addition">\
                    by <b>' + remoteData.new_video[i].name + '</b>\
                    <br>\
                    Views <b>' + remoteData.new_video[i].views + '</b>\
                  </div>\
                </div>\
              </a>\
            </div>';
            }
        }

        $('#list').show();
        $('#play').hide();
        $('.aside-video-list').html(videoItem);
        $('.popup-cover').click();
    });
}

function getVideoFileById(id) {
    $.getJSON(service + 'getVideoFileById', {video_id: id}, function (remoteData) {
        $('#video-page-videoselfie-container').show();
        $('#home-videoselfie-container').hide();
        var data = remoteData.data[0];
        console.log(data);
        var videoBox = '<video poster="' + host + data.video_src + '.jpg" controls width="100%">\
					<source src="' + host + data.video_src + '" />\
				</video>';
        $('.video-player-box').html(videoBox);

        $('.video-about-title').html(data.title + '\
                    <div class="by-user-name">\
                      ' + data.author_name + '\
                    </div>');

        $('.video-views-counter').text('Views ' + data.views);
        $('.btn-like span').text(data.likes);
        $('.btn-like').attr('data-id', id);
        $('.btn-dislike').attr('data-id', id);
        $('.btn-dislike span').text(data.dislikes).attr('data-id', id);
        $('.video-description').text(data.description);
        $('.video-info b:first').text(data.created_at);
        $('.video-info b:last').text(data.category_name);
        getVideoComments(id);
    });
}

function getVideoFileByIdBroadcast(id) {
    $('#broadcast-video-container').show();
    $('#broadcast-home-container').hide();
    var videoBox = '<iframe frameborder="0" style="width:100%" src="' + id + '/embed.html?dvr=true">�</iframe>';
    $('.video-player-box-broad').html(videoBox);
    getVideoComments(id);
}


$(document).ready(function () {

    initContainer();

    $('.btn-like').click(function () {
        addVideoLike($(this).data('id'), $(this));
    });

    $('.btn-dislike').click(function () {
        addVideoDislike($(this).data('id'), $(this));
    });

    $('.logout').click(function (e) {
        e.preventDefault();
        logout();
    });


});


