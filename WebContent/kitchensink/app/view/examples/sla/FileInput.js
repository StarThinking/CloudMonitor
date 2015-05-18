Ext.require([
    'Ext.slider.*', 
    'Ext.form.*',
    'Ext.window.MessageBox'
]);
var formPanel = Ext.create('Ext.form.Panel', {
    width: 400,
    height: 160,
    title: 'Sound Settings',
    bodyPadding: 10,
    defaultType: 'sliderfield',
    defaults: {
        anchor: '95%',
        tipText: function(thumb){
            return String(thumb.value) + '%';
        } 
    },
    items: [{
        fieldLabel: 'Sound Effects',
        value: 50,
        name: 'fx'
    },{
        fieldLabel: 'Ambient Sounds',
        value: 80,
        name: 'ambient'
    },{
        fieldLabel: 'Interface Sounds',
        value: 25,
        name: 'iface'
    }]
});

Ext.define('KitchenSink.view.examples.sla.FileInput', {
    extend: 'KitchenSink.view.examples.Example',
    requires: [
        'Ext.form.FieldSet',
        'Ext.form.Panel',
        'Ext.form.field.ComboBox',
        'Ext.form.field.Date',
        'Ext.form.field.Text',
        'Ext.slider.*',
        'KitchenSink.store.States',
        'Ext.util.JSON.*'
    ],

    items: [{
        xtype: 'form',

        width: 500,
        height: 500,
        frame: true,
        title: 'Input SLA',
        bodyPadding: 13,
        autoScroll:true,

        fieldDefaults: {
            labelAlign: 'right',
            labelWidth: 150,
            msgTarget: 'side'
        },

        items: [{
            xtype: 'fieldset',
            title: 'File Input',
            defaults: {
                width: 400
            },
            items: [{
                	id: 'slafile',
                    xtype: 'filefield',
                    name: 'slafile',
                    fieldLabel: 'File upload'
                }
            ]
        }],

        buttons: [{
            text: 'Submit',
            disabled: true,
            formBind: true,
            handler: function() {
            	var slafile = Ext.getCmp("slafile").getValue();
            	
            	Ext.Ajax.request({
                    url: '/CloudMaster/getSla',
                    
                    params: {
                    	slafile: slafile
                    },
                    Method: "post",
                    success: function(resp, opts) {
                    	alert(resp.responseText);
                    	var respText = Ext.decode(resp.responseText);
                    	alert(respText);
                    	Ext.Msg.alert('Success', respText.info);
                    },
                    failure: function(resp, opts) {
                    	var respText = Ext.decode(resp.responseText);
                    	Ext.Msg.alert('Error', respText.error);
                	}
            	});
            },
        }]

    }]
});
