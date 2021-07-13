/* ======= Animations ======= */
jQuery(document).ready(function($) {

    //Only animate elements when using non-mobile devices    
    if (isMobile.any === false) { 
    
        /* Animate elements in #Promo */
        $('#promo .headline').css('opacity', 0).one('inview', function(isInView) {
            if (isInView) {$(this).addClass('animated fadeInUp delayp1');}
        });
        $('#promo .product-holder').css('opacity', 0).one('inview', function(isInView) {
            if (isInView) {$(this).addClass('animated fadeInLeft delayp2');}
        });
    
        /* Animate elements in #About */
        $('#about .section-title').css('opacity', 0).one('inview', function(event, isInView) {
            if (isInView) {$(this).addClass('animated fadeInUp delayp1');}
        });
        
        $('#about .figure-right').css('opacity', 0).one('inview', function(isInView) {
            if (isInView) {$(this).addClass('animated fadeInRight delayp2');}
        });
        
        $('#about .figure-left').css('opacity', 0).one('inview', function(isInView) {
            if (isInView) {$(this).addClass('animated fadeInLeft delayp2');}
        });
        
        /* Animate elements in #Rewards */
        $('#rewards .section-title').css('opacity', 0).one('inview', function(event, isInView) {
            if (isInView) {$(this).addClass('animated fadeInUp delayp1');}
        });
        
        $('#rewards .item-1').css('opacity', 0).one('inview', function(event, isInView) {
            if (isInView) {$(this).addClass('animated fadeInUp delayp1');}
        });
        
        $('#rewards .item-2').css('opacity', 0).one('inview', function(event, isInView) {
            if (isInView) {$(this).addClass('animated fadeInUp delayp2');}
        });
        
        $('#rewards .item-3').css('opacity', 0).one('inview', function(event, isInView) {
            if (isInView) {$(this).addClass('animated fadeInUp delayp3');}
        });
        
        $('#rewards .item-4').css('opacity', 0).one('inview', function(event, isInView) {
            if (isInView) {$(this).addClass('animated fadeInUp delayp4');}
        });
        
        $('#rewards .item-5').css('opacity', 0).one('inview', function(event, isInView) {
            if (isInView) {$(this).addClass('animated fadeInUp delayp5');}
        });
        $('#rewards .item-6').css('opacity', 0).one('inview', function(event, isInView) {
            if (isInView) {$(this).addClass('animated fadeInUp delayp6');}
        });
        
        /* Animate elements in #Updates */
        $('#updates .section-title').css('opacity', 0).one('inview', function(event, isInView) {
            if (isInView) {$(this).addClass('animated fadeInUp delayp1');}
        });
        
        /* Animate elements in #Timeline */
        $('#timeline .section-title').css('opacity', 0).one('inview', function(event, isInView) {
            if (isInView) {$(this).addClass('animated fadeInUp delayp1');}
        });
        
        $('#timeline .timeline-item').css('opacity', 0).one('inview', function(event, isInView) {
            if (isInView) {$(this).addClass('animated fadeInUp delayp2');}
        });
        
        /* Animate elements in #team */
        $('#team .section-title').css('opacity', 0).one('inview', function(event, isInView) {
            if (isInView) {$(this).addClass('animated fadeInUp delayp1');}
        });
        
        $('#team .profile-holder').css('opacity', 0).one('inview', function(event, isInView) {
            if (isInView) {$(this).addClass('animated fadeInUp delayp2');}
        });
        
        /* Animate elements in #contact */
        $('#contact .section-title').css('opacity', 0).one('inview', function(event, isInView) {
            if (isInView) {$(this).addClass('animated fadeInUp delayp1');}
        });
        
        
        /* Animate elements in #faq */
        $('#faq .section-title').css('opacity', 0).one('inview', function(event, isInView) {
            if (isInView) {$(this).addClass('animated fadeInUp delayp1');}
        });
        
        
    }


});