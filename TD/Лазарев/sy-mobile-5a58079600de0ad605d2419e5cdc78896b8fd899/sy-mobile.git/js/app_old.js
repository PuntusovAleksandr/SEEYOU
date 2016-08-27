host = "http://seeyou.bod.com.ua";
service = host + "/api/";
apiKey = "demo";

function checkAuth() {

 $("input:[name=email]");
 $("input:[name=password");
}

function getIndex(category, id) {
  if (id){
        $.getJSON( service+'video/'+category+'/'+id, function(remoteData){
    console.log(remoteData);
    
    $('.video-player').html('<video controls="" width="100%" name="media"><source src="'+host+remoteData.file.src+'" type="video/mp4"></video>');
    $('.video-about-title').html(remoteData.file.title+'<div class="by-user-name">\
          '+remoteData.file.user_name+'\
        </div>');
    //console.log(remoteData.file.user_name);
    $('.video-views-counter').text(remoteData.file.views);
    $('.video-description').text(remoteData.file.description);
    $('.video-info').html('<div class="video-info">\
                            Published <b>'+remoteData.file.created_at+'</b> &emsp; Category <b><a href="#category/slug='+remoteData.file.category_slug+'">'+remoteData.file.category_name+'</a></b>\
                           </div>');
    var videoItem = '';
    
    for (var i = 0; i < remoteData.video.length; i++) {
         if (remoteData.video[i].id != id ){
              videoItem += '<div class="video-item">\
              <a href="#video/id='+remoteData.video[i].id+'" onclick="getIndex(\''+remoteData.video[i].slug+'\', '+remoteData.video[i].id+')" class="video-link">\
                <div class="video-item-preview">\
                  <img src="'+host+remoteData.video[i].src+'.jpg">\
                </div>\
                <div class="video-item-about">\
                  <div class="video-item-title" title="'+remoteData.video[i].title+'">\
                   '+remoteData.video[i].title+'\
                  </div>\
                  <div class="video-item-addition">\
                    by <b>'+remoteData.video[i].name+'</b>\
                    <br>\
                    Views <b>'+remoteData.video[i].views+'</b>\
                  </div>\
                </div>\
              </a>\
            </div>';
      }
    };

      var commentsItem = '';
    
    for (var i = 0; i < remoteData.comments.length; i++) {
         if (remoteData.comments[i].type == '1' ){
             commentsItem += '<div class="comments-item">\
            <div class="comments-user-icon">\
              <img src="'+remoteData.comments[i].user_avatar+'">\
            </div>\
            <div class="comments-content">\
              <div class="comments-user-name">\
                <a href="#">'+remoteData.comments[i].user_name+'</a> <span class="t-date">'+moment(remoteData.comments[i].created_at, "YYYY-MM-dd hh:mm:ss").fromNow()+'</span>\
              </div>\
              <div class="comments-text">\
               '+remoteData.comments[i].text+'\
              </div>\
              <div class="comments-replay-btn">\
                <a href="#">Replay</a>\
              </div>\
            </div>\
          </div>';
      } else {
            commentsItem += '<div class="comments-sub-list"><div class="comments-item">\
            <div class="comments-user-icon">\
              <img src="'+remoteData.comments[i].user_avatar+'">\
            </div>\
            <div class="comments-content">\
              <div class="comments-user-name">\
                <a href="#">'+remoteData.comments[i].user_name+'</a> <span class="t-date">'+moment(remoteData.comments[i].created_at, "YYYY-MM-dd hh:mm:ss").fromNow()+'</span>\
              </div>\
              <div class="comments-text">\
               '+remoteData.comments[i].text+'\
              </div>\
              <div class="comments-replay-btn">\
                <a href="#">Replay</a>\
              </div>\
            </div>\
          </div></div>';
      }
    };
    $('#list').hide();
    $('#play').show();
    $('.comments-main').html(commentsItem);
    $('.videoitem').html(videoItem);

    });
 
    
  } else {
    $.getJSON( service+'index', function(remoteData){
    console.log(remoteData);

    $('.video-player').html('<video controls="" width="100%" name="media"><source src="'+host+remoteData.file.src+'" type="video/mp4"></video>');
    $('.video-about-title').html(remoteData.file.title+'<div class="by-user-name">\
          '+remoteData.file.user_name+'\
        </div>');
    //console.log(remoteData.file.user_name);
    $('.video-views-counter').text(remoteData.file.views);
    $('.video-description').text(remoteData.file.description);
    $('.video-info').html('<div class="video-info">\
                            Published <b>'+moment(remoteData.file.created_at, "YYYY-MM-dd hh:mm:ss").fromNow()+'</b> &emsp; Category <b><a href="#category/slug='+remoteData.file.category_slug+'">'+remoteData.file.category_name+'</a></b>\
                           </div>');
    });
        $('#list').hide();
    $('#play').show();
  }  
}

function getVideoSelfie() {
  $.getJSON( service+'videoselfie', function(remoteData){
    //console.log(remoteData);
    var html = '';
    
    for (var i = 0; i < remoteData.categories.length; i++) {
      // console.log(remoteData.categories[i].id);
      html += '<li>\
                <a href="#categories/id='+remoteData.categories[i].id+'" onclick="getVideoFilesByCategory(\''+remoteData.categories[i].slug+'\')" class="nav-item">'+remoteData.categories[i].name+'</a>\
              </li>';
    };
 
    $('.category').html(html);

    var videoItem = '';
    
    for (var i = 0; i < remoteData.new_video.length; i++) {
      videoItem += '<div class="video-item">\
              <a href="#video/id='+remoteData.new_video[i].id+'" onclick="getIndex(\''+remoteData.new_video[i].slug+'\', '+remoteData.new_video[i].id+')" class="video-link">\
                <div class="video-item-preview">\
                  <img src="'+host+remoteData.new_video[i].src+'.jpg">\
                </div>\
                <div class="video-item-about">\
                  <div class="video-item-title" title="'+remoteData.new_video[i].title+'">\
                   '+remoteData.new_video[i].title+'\
                  </div>\
                  <div class="video-item-addition">\
                    by <b>'+remoteData.new_video[i].name+'</b>\
                    <br>\
                    Views <b>'+remoteData.new_video[i].views+'</b>\
                  </div>\
                </div>\
              </a>\
            </div>';
    };
    $('#list').hide();
    $('#play').show();
    $('.videoitem').html(videoItem);
  //console.log(html);
  });
}

function getVideoFilesByCategory(category, id) {
    $.getJSON( service+'videofilesbycategory'+'/'+category, function(remoteData){
    console.log(remoteData);
    var videoItem = '';
    for (var i = 0; i < remoteData.new_video.length; i++) {
      if (remoteData.new_video[i].id != id ){

      videoItem += '<div class="video-item">\
              <a href="#video/id='+remoteData.new_video[i].id+'" onclick="getIndex(\''+remoteData.new_video[i].slug+'\', '+remoteData.new_video[i].id+')" class="video-link">\
                <div class="video-item-preview">\
                  <img src="'+host+remoteData.new_video[i].src+'.jpg">\
                </div>\
                <div class="video-item-about">\
                  <div class="video-item-title" title="'+remoteData.new_video[i].title+'">\
                   '+remoteData.new_video[i].title+'\
                  </div>\
                  <div class="video-item-addition">\
                    by <b>'+remoteData.new_video[i].name+'</b>\
                    <br>\
                    Views <b>'+remoteData.new_video[i].views+'</b>\
                  </div>\
                </div>\
              </a>\
            </div>';
          }
    };
    $('#list').show();
    $('#play').hide();
    $('.aside-video-list').html(videoItem);
    $('.popup-cover').click();
  });
}

function showVideo() {
  $.getJSON( service+'video', function(remoteData){
  console.log(remoteData);

  
  });
}







