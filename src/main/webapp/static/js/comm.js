$(document).ready(function () {

   //nav
    var obj=null;
    var As=document.getElementById('starlist').getElementsByTagName('a');
    obj = As[0];
    for(i=1;i<As.length;i++){if(window.location.href.indexOf(As[i].href)>=0)
    obj=As[i];}
    obj.id='selected';

     //nav
  $("#mnavh").click(function(){
    $("#starlist").toggle();
  $("#mnavh").toggleClass("open");
  });

    //search
  $(".searchico").click(function(){
  $(".search").toggleClass("open");
  });

   //searchclose
    $(".searchclose").click(function(){
  $(".search").removeClass("open");
  });

  //banner
    $('#banner').easyFader({slideDur:10000});

   //nav menu
   $(".menu").click(function(event) {
   $(this).children('.sub').slideToggle();
   });

   $('.tab_buttons li').live('click',function(){
     $(this).addClass('newscurrent').siblings().removeClass('newscurrent');
     $('.newstab>div:eq('+$(this).index()+')').show().siblings().hide();
   });

});

(function(a){function h(b){for(var a=["Webkit","Moz","O","ms"],c=0;c<a.length;c++)if(a[c]+"Transition"in b.style)return"-"+a[c].toLowerCase()+"-";return"transition"in b.style?"":!1}a.fn.removeStyle=function(b){return this.each(function(){var h=a(this);b=b.replace(/\s+/g,"");var c=b.split(",");a.each(c,function(){var a=RegExp(this.toString()+"[^;]+;?","g");h.attr("style",function(b,c){if(c)return c.replace(a,"")})})})};var t=function(b){return this.each(function(){function n(a,b){function j(){f.eq(a).removeStyle("opacity, z-index");
    f.eq(b).removeStyle(h+"transition, transition");k=b;p=l=!1;q=setTimeout(function(){c("next")},d.slideDur);"function"==typeof d.onFadeEnd&&d.onFadeEnd.call(this,f.eq(k))}if(l||a==b)return!1;l=!0;"function"==typeof d.onFadeStart&&!p&&d.onFadeStart.call(this,f.eq(e));r.removeClass("active").eq(e).addClass("active");f.eq(a).css("z-index",2);f.eq(b).css("z-index",3);if(h){var g={};g[h+"transition"]="opacity "+d.fadeDur+"ms";g.opacity=1;f.eq(b).css(g);setTimeout(function(){j()},d.fadeDur)}else f.eq(b).animate({opacity:1},
    d.fadeDur,function(){j()})}function c(a){"next"==a?(e=k+1,e>m-1&&(e=0)):"prev"==a?(e=k-1,0>e&&(e=m-1)):e=a;n(k,e)}var d={slideDur:7E3,fadeDur:800,onFadeStart:null,onFadeEnd:null};b&&a.extend(d,b);this.config=d;var j=a(this),l=!1,p=!0,q,k,e,f=j.find(".slide"),m=f.length,s=j.find(".pager_list");h=a.support.leadingWhitespace?h(j[0]):!1;for(var g=0;g<m;g++)s.append('<li class="pic_page" data-target="'+g+'">'+g+"</li>");j.find(".pic_page").bind("click",function(){var b=a(this).attr("data-target");clearTimeout(q);
    c(b)});var r=s.find(".pic_page");r.eq(0).addClass("active");n(1,0)})};a.fn.easyFader=function(a){return t.apply(this,arguments)}})(jQuery);

