var currentPage={initialize:function(){this.bindEvents()},bindEvents:function(){},onDeviceReady:function(){},initComunication:function(){$(document).ready(function(){$("#txtFec_nac").datepicker({format:"dd/mm/yyyy",autoclose:!0,language:"es",startView:2}),currentPage.callCompany()})},callCompany:function(){showScreen();var n=new Firebase("https://burning -torch-5915.firebaseio.com/asistenciaviaje/companias"),e=new Array;n.orderByChild("orden").on("value",function(n){n.forEach(function(n){var o=(n.key(),n.val());e.push(o),console.log("-----------------------------"),console.log(o.nombre),console.log(o.VDN),console.log(o.telefono),$("#txtPrestador_reg_av").append($("<option>",{value:o.VDN,text:o.nombre}))}),localStorage.setItem("companies",JSON.stringify(e))}),EA.init(),hideScreen()}};currentPage.initComunication();