/*
 * p72.html
 */


$(document).ready(function(){
	
		let getRandom = function(num){
			let my_num = Math.floor(Math.random()*num);
			return my_num;
		};
		
		let hideCode = function(){
			let numRand = getRandom(4); // 0, 1, 2
			numRand = 0;
			$(".guess_box").each(function(index, item){
				if(numRand === index){
					$(this).append("<span id='has_discount'></span>");
					return false;
				}
			});
		};
		
		hideCode();
		
		let checkForCode = function(){
			let discount = "";
			if($.contains(this, document.getElementById("has_discount") ) ){
				let my_num = getRandom(100);
				discount = "<p>Your Code : CODE"+new Date().getTime()+"  "+my_num+"%</p>";
			}	else {
				discount = "<p>Sorry, no discount this time!<p>";
			}
		
			$(this).append(discount);
			
			$(".guess_box").each(function(){
				if($.contains(this, document.getElementById("has_discount") ) ){
					$(this).addClass("discount");
				}	else	{
					$(this).addClass("no_discount");
				}
				$(this).unbind("click");
		
		});
	};
		
		$(".guess_box").click(checkForCode);
});		
	
	/*$(".guess_box").click(function(){
		$(".guess_box p").remove();
		//랜덤숫자 생성
		let discount = Math.floor(Math.random()*6)+5;
		console.log(discount);
		let discount_msg = "<p>Your Discount is "+ discount +"%</p>";
		$(this).append(discount(discount_msg));
		
		$(".guess_box").each(function(){
			$(this).unbind("click");
		});
	});*/
