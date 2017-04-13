$(function() {

	// navbar notification popups
	$(".notification-dropdown").each(function(index, el) {
		var $el = $(el);
		var $dialog = $el.find(".pop-dialog");
		var $trigger = $el.find(".trigger");

		$dialog.click(function(e) {
			e.stopPropagation()
		});
		$dialog.find(".close-icon").click(function(e) {
			e.preventDefault();
			$dialog.removeClass("is-visible");
			$trigger.removeClass("active");
		});
		$("body").click(function() {
			$dialog.removeClass("is-visible");
			$trigger.removeClass("active");
		});

		$trigger.click(function(e) {
			e.preventDefault();
			e.stopPropagation();

			// hide all other pop-dialogs
			$(".notification-dropdown .pop-dialog").removeClass("is-visible");
			$(".notification-dropdown .trigger").removeClass("active")

			$dialog.toggleClass("is-visible");
			if ($dialog.hasClass("is-visible")) {
				$(this).addClass("active");
			} else {
				$(this).removeClass("active");
			}
		});
	});

	// mobile side-menu slide toggler
	var $menu = $("#sidebar-nav");
	$("body").click(function() {
		if ($(this).hasClass("menu")) {
			$(this).removeClass("menu");
		}
	});
	$menu.click(function(e) {
		e.stopPropagation();
	});
	$("#menu-toggler").click(function(e) {
		e.stopPropagation();
		$("body").toggleClass("menu");
	});


	$(window).resize(function() {
    	resize(this);
    });
    
    function resize(window){
    	if($(window).width() > 769) {
    		$("body.menu").removeClass("menu");
    	}
    	
    	var height = $(window).height();
    	//alert('width:'+width+'-height:'+height);
    	height = height-60;
    	$('#resultFrame').css('height',height+'px');
        
    	
    	var $dashboard_menu = $('#dashboard-menu');
    	$dashboard_menu.attr('style','');
    	$('.slimScrollDiv').remove();
    	$('#sidebar-nav').prepend($dashboard_menu);
    	
		$("#dashboard-menu .def-menu").click(function(e) {
			//e.preventDefault();
			$(".active").removeClass("active");
			var $item = $(this);
			$item.addClass("active");
			$item.prepend($('#pointer'));
		});
		// sidebar menu dropdown toggle
		$("#dashboard-menu .dropdown-toggle").click(function(e) {
			e.preventDefault();
			var $item = $(this).parent();
			
			if($item.find(".submenu").css('display')=='none'){
				$item.find(".submenu").slideDown("fast");
			}else{
				$item.find(".submenu").slideUp("fast");
			}

		});
		
		$('#dashboard-menu').slimScroll({
			height : height + 'px',
			color : '#6e829b'
		});
	}
	resize(window);
	
	//当一级菜单没有二级菜单时隐藏一级菜单
	$(".icon-chevron-down").each(function(){
		var length = $(this).parents(".def-menu").find("li").length;
		if(length == 0){
			 $(this).parents(".def-menu").hide();
		}
	});

});