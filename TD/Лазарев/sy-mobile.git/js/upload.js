function strstr(str, needle){
	if(str.indexOf(needle) + 1) {
		return true;
	}
 return false;
}
$(document).ready(function() {

		maxFileSize = 125000000; // максимальный размер файла - 125 мб.
		var files;

		var globalData = {};


	function checkFile(file) {

		if (file.size > maxFileSize) {
			console.log(file.size);
			$("#message").text('File size is too large');
			return false;
		}
		else if(!strstr(file.type, 'video')){
			$("#message").text('File format is not supported');
			return false;
		}
		else {
			$("#message").text('File is selected');
			return true;
		}
	}

	function checkFormFields(form, messageSelector) {
		var errors = false;
		form.find('.data-validate').each(function(){
			if($(this).val() == ''){
				messageSelector.text($(this).attr('data-error-msg'));
				errors = true;
				return false;
			}
		});
		if(errors){
			return false;
		}
		return true;
	}

	$('input[type=file]').on('change', prepareUpload);

	function prepareUpload(event) {
		var file = event.target.files[0];
		if(checkFile(file)) {
			files = event.target.files;
		}
	}

	function uploadProgress(event) {
		var percent = parseInt(event.loaded / event.total * 100);
		$("#message").text('downloading'+': ' + percent + '%');
		if(percent == 100) {
			$("#message").text('file is converting');
		}
	}


	$('form#video-upload').submit(function(e) {
		e.preventDefault();

		var submitBtn = $(this).find('button[type=submit]');
		submitBtn.disable();
		e.preventDefault();

		if(typeof files === "undefined") {
			$("#message").text('select your file');
			submitBtn.enable();
			return false;
		}

		if( !checkFormFields($('form#video-upload'), $("#message")) ) {
			submitBtn.enable();
			return false;
		}
		console.log(123);
		var _orgAjax = jQuery.ajaxSettings.xhr;

		jQuery.ajaxSettings.xhr = function () {
			var xhr = _orgAjax();
			xhr.upload.addEventListener('progress', uploadProgress, false);
			//xhr.onreadystatechange = stateChange;
			return xhr;
		};

		var aploadData = new FormData();

		$.each(files, function(key, value)
		{
			aploadData.append(key, value);
		});

		$.ajax({
			url: host+'api/videoUpload',
			type: 'POST',
			data: aploadData,
			cache: false,
			dataType: 'json',
			xhrFields: {
				withCredentials: true
			},
			processData: false, // Don't process the files
			contentType: false, //'multipart/form-data', // Set content type to false as jQuery will tell the server its a query string request

			beforeSend: function (xhr) {
				$(".validation-errors").hide().empty();
				var token = $('meta[name="csrf_token"]').attr('content');
				if (token) {
					return xhr.setRequestHeader('X-CSRF-TOKEN', token);
				}
			},
			success: function(data) {

					console.log(data);
					$("#message").text('file is uploaded');
				preSubmitForm(e, data, submitBtn); // после того как файл загружен делаем повторную отправку формы с полями описания видео

			},
			error: function(xhr, textStatus, thrownError) {
				$("#message").text('something went wrong, try again later');
				console.log(xhr);
				console.log(textStatus);
				console.log(thrownError);
			}
		});

		return false;
	});

	// проверяем нужно ли обрезать видео
	function preSubmitForm(event, data, button) {
		console.log(data);
		/*if(parseInt(data.fileDuration) > 60) {

			globalData.event = event;
			globalData.data = data;
			globalData.button = button;
			initJPlayer("#jquery_jplayer_1", host+"upload/"+data.fileName+"", host+"upload/"+data.fileName+".jpg", false);
			$('.video_cut_box').show();
			$('.video_data_box').hide();
		}
		else {
			submitForm(event, data, button);
		}*/
		submitForm(event, data, button);
	}

	function submitForm(event, data, button) {

	//	console.log(data);
	// filename = data.fileName;
		// Create a jQuery object from the form
		var form = $(event.target);

		// Serialize the form data
		var formData = form.serialize();
		formData = formData + '&filename=' + data.fileName;

		$.ajax({
			url: host+'api/videoInsert',
			type: 'POST',
			data: formData,
			cache: false,
			dataType: 'json',
			xhrFields: {
				withCredentials: true
			},

			beforeSend: function (xhr) {
				$(".validation-errors").hide().empty();

				var token = $('meta[name="csrf_token"]').attr('content');
				if (token) {
					return xhr.setRequestHeader('X-CSRF-TOKEN', token);
				}
			},
			success: function(data)
			{
				console.log(data);
		/*		button.enable();
				document.getElementById(form.attr('id')).reset();
				$("#message").text('file is uploaded successfully and waiting for moderation');
				$('#dropzone').html('<div style="text-align: center"><img style="height: 296px;" src="/upload/'+data.fileName+'.jpg" /></div>')




			$("#jquery_jplayer_1").jPlayer("destroy");
				$('.video_cut_box').html('');


				$('.video_result_box').html('<div id="jp_container_1" class="jp-video jp-video-360p" role="application" aria-label="media player"><div class="jp-type-single"><div id="jquery_jplayer_1" class="jp-jplayer"></div><div class="jp-gui"><div class="jp-video-play"><button class="jp-video-play-icon" role="button" tabindex="0">play</button></div><div class="jp-interface"><div class="jp-progress"><div class="jp-seek-bar"><div class="jp-play-bar"></div></div></div><div class="jp-current-time" role="timer" aria-label="time">&nbsp;</div><div class="jp-duration" role="timer" aria-label="duration">&nbsp;</div><div class="jp-controls-holder"><div class="jp-controls"><button class="jp-play" role="button" tabindex="0">play</button><button class="jp-stop" role="button" tabindex="0">stop</button></div><div class="jp-volume-controls"><button class="jp-mute" role="button" tabindex="0">mute</button><button class="jp-volume-max" role="button" tabindex="0">max volume</button><div class="jp-volume-bar"><div class="jp-volume-bar-value"></div></div></div><div class="jp-toggles"><button class="jp-repeat" role="button" tabindex="0">repeat</button><button class="jp-full-screen" role="button" tabindex="0">full screen</button></div></div><div class="jp-details"><div class="jp-title" aria-label="title">&nbsp;</div></div></div></div><div class="jp-no-solution"><span>Update Required</span>To play the media you will need to either update your browser to a recent version or update your <a href="http://get.adobe.com/flashplayer/" target="_blank">Flash plugin</a>.</div></div></div>');

				initJPlayer("#jquery_jplayer_1", "http://"+window.location.hostname+"/upload/"+data.fileName, "http://"+window.location.hostname+"/upload/"+data.fileName+".jpg");
				$('.video_data_box').hide();
				$('.video_result_box').show();
				$('.next-video-link-upload').show();*/

			},
			error: function(xhr, textStatus, errorThrown)
			{
				button.enable();
				document.getElementById(form.attr('id')).reset();
				$("#message").text('DB insert file error');
				console.log(xhr);
				console.log(textStatus);
				console.log(errorThrown);
			}
		});
	}


	/* Movie cut functions begin */

//	function initJPlayer(video, poster) {
	function initJPlayer(selector, video, poster, loaddata) {

		$(selector).jPlayer("destroy");

		$(selector).jPlayer({
			ready: function () {
				$(this).jPlayer("setMedia", {
					m4v: video,
					poster: poster
				});
			},
			ended: function () {
			},
			loadeddata: function (event) { // calls after setting the song duration
				if(!loaddata) {
					var duration = event.jPlayer.status.duration;
					$("#max-duration").val(parseInt(duration * 10));
					sliderRangeInit(duration * 10);
				}
			},
			supplied: "webmv, ogv, m4v, mp4",
			size: {
				width: "562px",
				height: "320px"
			},
			useStateClassSkin: true,
			autoBlur: false,
			smoothPlayBar: true,
			keyEnabled: true,
			remainingDuration: true,
			toggleDuration: true
		});

	}



	$('#next').click(function (e) {
		e.preventDefault();
		submitForm(globalData.event, globalData.data, globalData.button);
		$('.video_cut_box').hide();
		$('.video_data_box').show();
		//console.log(globalData);
	});

	$('#apply').click(function (e) {
		e.preventDefault();
		var maxDuration = parseInt($("#max-duration").val());
		var container = $("#slider-range");
		var needDuration = 600;
		var rightLimit = parseInt(container.slider("values")[1]);
		var leftLimit = parseInt(container.slider("values")[0]);
		var currentDuration = rightLimit - leftLimit;
		if(currentDuration > needDuration) {

			rightLimit = rightLimit - (currentDuration - needDuration);
			container.slider("values", 1, rightLimit);
		}
		else if(currentDuration < needDuration) {
			var needle = needDuration - currentDuration;
			if((maxDuration-needle) > rightLimit) {
				rightLimit += needle;
				container.slider("values", 1, rightLimit);
			}
			else {
				rightLimit = maxDuration;
				leftLimit = maxDuration - needDuration;
				container.slider("values", 0, leftLimit);
				container.slider("values", 1, rightLimit);
			}
		}

		$('.ui-widget-header').removeClass('danger');

		$("#left_limit").val(timeFormat(leftLimit * 100));
		$("#right_limit").val(timeFormat(rightLimit * 100));
		$('#next').show();
		//console.log(maxDuration);
	});


	// устанавливает кадр при движении бегунка
	function setFrame(sec) {
		$("#jquery_jplayer_1").jPlayer("play", sec);
		$.jPlayer.pause();
	}


	var timeFormat = (function () {
		function num(val) {
			val = Math.floor(val);
			return val < 10 ? '0' + val : val;
		}

		return function (ms/**number*/) {
			var sec = ms / 1000
				, hours = sec / 3600 % 24
				, minutes = sec / 60 % 60
				, seconds = sec % 60
				;

			return num(hours) + ":" + num(minutes) + ":" + num(seconds);
		};
	})();

	function sliderRangeInit(duration) {
		var container = $("#slider-range");
		container.slider({
			range: true,
			isRTL: isRTL, // определяется перед скриптами для всего сайта
			min: 0,
			max: duration,
			values: [0, 600],
			slide: function (event, ui) {
				$("#left_limit").val(timeFormat(ui.values[0] * 100));
				$("#right_limit").val(timeFormat(ui.values[1] * 100));
				//
				var needDuration = 600;
				var rightLimit = (ui.values[1]);
				var leftLimit = (ui.values[0]);
				var currentDuration = rightLimit - leftLimit;
				if(currentDuration > needDuration) {
					$('.ui-widget-header').addClass('danger');
				}
				else {
					$('.ui-widget-header').removeClass('danger');
				}
				setFrame(ui.value / 10);
			}
		});
		$("#left_limit").val(timeFormat(container.slider("values", 0) * 100));
		$("#right_limit").val(timeFormat(container.slider("values", 1) * 100));
	}

	/* Movie cut functions end */

});


