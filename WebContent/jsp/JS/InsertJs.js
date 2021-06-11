/**
 * 
 */
const $form=
document.getElementById('form');

$form.addEventListener('change',(e)=>{
	let type=e.target.type;
	console.log('onChange',type);
	
});

$form.addEventListener('input',(e)=>{
	let type=e.target.type;
	console.log('onInput',type);
});