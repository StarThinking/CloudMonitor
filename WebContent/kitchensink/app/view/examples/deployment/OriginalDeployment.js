Ext.define('KitchenSink.view.examples.deployment.OriginalDeployment', {
    extend: 'KitchenSink.view.examples.PanelExample',

    items: [
        {
            xtype: 'panel',
			width: 980,
			height: 565,
			
			html: '<iframe width=800, height=600 src="app/node.html"></iframe>'
        }
    ]
});
