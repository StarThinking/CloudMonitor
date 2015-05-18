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

Ext.define('KitchenSink.view.examples.sla.UserInput', {
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
            title: 'User Input',
            defaultType: 'textfield',
            defaults: {
                width: 400
            },
            items: [
                { allowBlank: true, fieldLabel: 'Response time (ms)', id: 'responsetime', name: 'responsetime', emptyText: 'Response time' },
//                {
//                	xtype: 'sliderfield',
//                	allowBlank: false,
//                	fieldLabel: 'Reliability',
//                	name: 'reliability',
//                    minValue: 0,
//                    maxValue: 100,
//                    value: 90
//                },
                { allowBlank: true, fieldLabel: 'Reliability (0~1)', id: 'reliability', name: 'reliability', emptyText: 'reliability' },
                { allowBlank: true, fieldLabel: 'TransitionPoint1', id: 'transitionPoint1', name: 'transitionPoint1', emptyText: 'transitionPoint1' },
                { allowBlank: true, fieldLabel: 'TransitionPoint2', id: 'transitionPoint2', name: 'transitionPoint2', emptyText: 'transitionPoint2' },
                { allowBlank: true, fieldLabel: 'RevenueCurveParameter', id: 'revenueCurveParameter', name: 'revenueCurveParameter', emptyText: 'revenueCurveParameter' }
            ]
        }, {
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
            	var responsetime = Ext.getCmp("responsetime").getValue();
            	var reliability = Ext.getCmp("reliability").getValue();
            	var transitionPoint1 = Ext.getCmp("transitionPoint1").getValue();
            	var transitionPoint2 = Ext.getCmp("transitionPoint2").getValue();
            	var revenueCurveParameter = Ext.getCmp("revenueCurveParameter").getValue();
            	
            	var slafile = Ext.getCmp("slafile").getValue();
            	
            	Ext.Ajax.request({
                    url: '/CloudMaster/getSla',
                    
                    params: {
                    	responsetime: responsetime,
                    	reliability: reliability,
                    	transitionPoint1: transitionPoint1,
                    	transitionPoint2: transitionPoint2,
                    	revenueCurveParameter: revenueCurveParameter,
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
