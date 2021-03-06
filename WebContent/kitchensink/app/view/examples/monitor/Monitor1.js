Ext.require(['Ext.data.*']);

/*window.generateData = function(n, floor){
	var data = [],
		p = (Math.random() *  11) + 1,
		i;
		
	floor = (!floor && floor !== 0)? 20 : floor;
	
	for (i = 0; i < (n || 12); i++) {
		data.push({
			name: Ext.Date.monthNames[i % 12],
			data1: Math.floor(Math.max((Math.random() * 100), floor)),
			data2: Math.floor(Math.max((Math.random() * 100), floor)),
			data3: Math.floor(Math.max((Math.random() * 100), floor)),
			data4: Math.floor(Math.max((Math.random() * 100), floor))
		});
	}
	return data;
};*/

//var store1 = Ext.create('Ext.data.JsonStore', {
var store1 = new Ext.data.JsonStore({
	//url: '/CloudMaster/getResponseTime',
	proxy: new Ext.data.HttpProxy({
		url: '/CloudMaster/getResponseTime',
        method: 'GET'
    }),
	autoLoad: true,
	root: 'root',
	fields: [{name: 'name', type: 'string'}, {name: 'data1', type: 'int'}]
	//data: generateData(12, 20)
});

function timedCount()
{
	setTimeout("timedCount()",30000);
	//store1.loadData(generateData());
	store1.load();
}

Ext.define('KitchenSink.view.examples.monitor.Monitor1', {
    extend: 'KitchenSink.view.examples.PanelExample',
	requires: [
        'Ext.chart.*',
		'Ext.layout.container.Fit',
		'Ext.window.MessageBox'
    ],
		
    items: [
        {
            xtype: 'panel',
			width: 800,
			height: 500,
			title: 'Line Chart',
			layout: 'fit',
			tbar: [{
				text: 'Save Chart',
				handler: function() {
					Ext.MessageBox.confirm('Confirm Download', 'Would you like to download the chart as an image?', function(choice){
						if(choice == 'yes'){
							Ext.getCmp("chart1").save({
								type: 'image/png'
							});
						}
					});
				}
			}, {
				text: 'Reload Data',
				handler: function() {
					store1.load();
					//store1.loadData(generateData());
				}
			}, {
				text: 'Reload Data (Real Time)',
				handler: function() {
					timedCount();
				}
			}],
			items: [
				{
					xtype: 'chart',
					id: 'chart1',
					animate: true,
					shadow: true,
					autoScroll: false,
					store: store1,
					axes: [{
						type: 'Numeric',
						position: 'left',
						fields: ['data1'],
						title: "Response Time",
						grid: true
					}, {
						type: 'Category',
						position: 'bottom',
						fields: 'name',
						title: "<--- Time"
					}],
					series: [{
						type: 'line',
						axis: 'left',
						gutter: 80,
						xField: 'name',
						yField: ['data1'],
						/*tips: {
							trackMouse: true,
							width: 580,
							height: 170,
							layout: 'fit',
							items: {
								xtype: 'container',
								layout: 'hbox',
								items: [pieChart, grid]
							},
							renderer: function(klass, item) {
								var storeItem = item.storeItem,
									data = [{
										name: 'data1',
										data: storeItem.get('data1')
									}, {
										name: 'data2',
										data: storeItem.get('data2')
									}, {
										name: 'data3',
										data: storeItem.get('data3')
									}, {
										name: 'data4',
										data: storeItem.get('data4')
									}, {
										name: 'data5',
										data: storeItem.get('data5')
									}], i, l, html;
								
								this.setTitle("Information for " + storeItem.get('name'));
								pieStore.loadData(data);
								gridStore.loadData(data);
								grid.setSize(480, 130);
							}
						}*/
					}]
				}
			]
        }
    ]
});
