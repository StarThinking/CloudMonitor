Ext.define('KitchenSink.view.List', {
    extend: 'Ext.tree.Panel',
    xtype: 'exampleList',
    
    requires: [
        'KitchenSink.store.Examples',
        'KitchenSink.view.examples.Example',
        'KitchenSink.view.examples.PanelExample',
        'KitchenSink.view.examples.user.Login',
        'KitchenSink.view.examples.user.Register',
        'KitchenSink.view.examples.deployment.OriginalDeployment',
        'KitchenSink.view.examples.deployment.RealtimeDeployment',
        'KitchenSink.view.examples.sla.UserInput',
        'KitchenSink.view.examples.sla.FileInput',
        'KitchenSink.view.examples.monitor.Monitor1',
        'KitchenSink.view.examples.monitor.Monitor2',
        'KitchenSink.view.examples.monitor.Monitor3',
        'KitchenSink.view.examples.monitor.Monitor4',
        'KitchenSink.view.examples.monitor.Monitor5',
        'KitchenSink.view.examples.monitor.Monitor6',

        'KitchenSink.view.examples.user.Contact',
        'KitchenSink.view.examples.grids.BasicGrid',
        'KitchenSink.view.examples.grids.GroupedGrid',
        'KitchenSink.view.examples.grids.GroupedHeaderGrid',
        'KitchenSink.view.examples.grids.LockedGrid',
        'KitchenSink.view.examples.tabs.BasicTabs',
        'KitchenSink.view.examples.tabs.FramedTabs',
        'KitchenSink.view.examples.tabs.IconTabs',
        'KitchenSink.view.examples.tabs.TitledTabPanels',
        'KitchenSink.view.examples.toolbars.BasicToolbar',
        'KitchenSink.view.examples.toolbars.DockedToolbar',
        'KitchenSink.view.examples.trees.BasicTree',
        'KitchenSink.view.examples.windows.BasicWindow'
    ],
    
    title: 'Menu',
    rootVisible: false,
	
	cls: 'examples-list',
    
    lines: false,
    useArrows: true,
    
    store: 'Examples'
});
