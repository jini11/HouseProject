/*______________________________________________________________________
  [ 메인 화면 효과 ]
______________________________________________________________________*/
/* 메인 화면 슬라이드 */

$('.img_sub>ul>li').hide();
$('.img_sub>ul>li:first-child').show();

setInterval(function(){
    $('.img_sub>ul>li:first-child').fadeOut()
    .next().fadeIn().end(1000)
    .appendTo('.img_sub>ul');
},4000);