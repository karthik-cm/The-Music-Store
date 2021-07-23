/* Handlebars Helper
 */

function load_handlebar_template(template_id, context_object, loader_id){
	// Get the Handlebar template 
	var template = $('#'+template_id).html();
	
	// Compile Handlebar temaplate
	var compiledTemplate = Handlebars.compile(template);
	
	// Final HTML
	var finalHtml = compiledTemplate(context_object);
	
	// Load to div
	$(loader_id).html(finalHtml);
}