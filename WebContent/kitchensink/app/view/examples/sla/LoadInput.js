Ext.require([
    'Ext.slider.*', 
    'Ext.form.*',
    'Ext.window.MessageBox'
]);


Ext.define('KitchenSink.view.examples.sla.LoadInput', {
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
            title: 'Load Input',
            defaultType: 'numberfield',
            defaults: {
                width: 400
            },
            items: [
                { hideTrigger: true, fieldLabel: 'Phrase1', id: 'duration1', name: 'duration1', emptyText: 'duration' },
                { hideTrigger: true, fieldLabel: ' ', id: 'count1', name: 'count1', emptyText: 'count' },
                { hideTrigger: true, fieldLabel: 'Phrase2', id: 'duration2', name: 'duration2', emptyText: 'duration' },
                { hideTrigger: true, fieldLabel: ' ', id: 'count2', name: 'count2', emptyText: 'count' },
                { hideTrigger: true, fieldLabel: 'Phrase3', id: 'duration3', name: 'duration3', emptyText: 'duration' },
                { hideTrigger: true, fieldLabel: ' ', id: 'count3', name: 'count3', emptyText: 'count' },
                { hideTrigger: true, fieldLabel: 'Phrase4', id: 'duration4', name: 'duration4', emptyText: 'duration' },
                { hideTrigger: true, fieldLabel: ' ', id: 'count4', name: 'count4', emptyText: 'count' },
                { hideTrigger: true, fieldLabel: 'Phrase5', id: 'duration5', name: 'duration5', emptyText: 'duration' },
                { hideTrigger: true, fieldLabel: ' ', id: 'count5', name: 'count5', emptyText: 'count' },
                { hideTrigger: true, fieldLabel: 'Phrase6', id: 'duration6', name: 'duration6', emptyText: 'duration' },
                { hideTrigger: true, fieldLabel: ' ', id: 'count6', name: 'count6', emptyText: 'count' }
            ]
        }],

        buttons: [{
            text: 'Submit',
            disabled: true,
            formBind: true,
            handler: function() {
            	var duration1 = Ext.getCmp("duration1").getValue();
            	var duration2 = Ext.getCmp("duration2").getValue();
            	var duration3 = Ext.getCmp("duration3").getValue();
            	var duration4 = Ext.getCmp("duration4").getValue();
            	var duration5 = Ext.getCmp("duration5").getValue();
            	var duration6 = Ext.getCmp("duration6").getValue();
            	var count1 = Ext.getCmp("count1").getValue();
            	var count2 = Ext.getCmp("count2").getValue();
            	var count3 = Ext.getCmp("count3").getValue();
            	var count4 = Ext.getCmp("count4").getValue();
            	var count5 = Ext.getCmp("count5").getValue();
            	var count6 = Ext.getCmp("count6").getValue();
            	
            	Ext.Ajax.request({
                    url: '/CloudMaster/putLoad',
                    
                    params: {
                    	duration1: duration1,
                    	duration2: duration2,
                    	duration3: duration3,
                    	duration4: duration4,
                    	duration5: duration5,
                    	duration6: duration6,
                    	count1: count1,
                    	count2: count2,
                    	count3: count3,
                    	count4: count4,
                    	count5: count5,
                    	count6: count6
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
