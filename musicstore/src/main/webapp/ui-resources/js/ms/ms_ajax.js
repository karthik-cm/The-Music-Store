/** Music Store Ajax funtions
 * @Author Karthik CM */ 

/** To load any given portlet
 * @param portletName
 * @param targetDiv
 * @param hideDiv
 * @returns
 */
function load_portlet(portletName, targetDiv, hideDiv){
	toggleLoader('show');
	if(portletName != null && targetDiv != null && hideDiv != null){
		setTimeout(function(){
			$.ajax({
				type : 'GET',
				url : 'loadPortlet',
				data : {
					'portletName' : portletName
				},
				success : function(response){
					var portlet = response;
					if(portlet != null){
						toggleLoader('hide');
						$('#'+targetDiv).html(portlet);
						$('#'+hideDiv).hide();
						$('#'+hideDiv).siblings('div').hide();
						$('#'+targetDiv).show();
					}
					else{
						toggleLoader('hide');
					}
				},
				error : function(response){
					toggleLoader('hide');
				}
			});
		}, 500);
	}
}