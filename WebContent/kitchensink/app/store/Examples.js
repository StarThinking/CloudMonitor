Ext.define('KitchenSink.store.Examples', {
    extend: 'Ext.data.TreeStore',

    root: {
        expanded: true,
        children: [
			{
                text: 'User',
                expanded: true,
                children: [
                    { leaf: true, text: 'Login' },
                    { leaf: true, text: 'Register' }
                ]
            },
            {
                text: 'Deployment',
                expanded: true,
                children: [
                    { leaf: true, text: 'Original Deployment' },
                    { leaf: true, text: 'Realtime Deployment' }
                ]
            },
            {
                text: 'Sla',
                expanded: true,
                children: [
                    { leaf: true, text: 'User Input' },
                    { leaf: true, text: 'File Input' }
                ]
            },
            {
                text: 'Monitor',
                expanded: true,
                children: [
                    { leaf: true, text: 'Monitor1' },
                    { leaf: true, text: 'Monitor2' },
                    { leaf: true, text: 'Monitor3' },
                    { leaf: true, text: 'Monitor4' }
                ]
            },/*
            {
                text: 'Grids',
                expanded: true,
                children: [
                    { leaf: true, text: 'Basic Grid' },
                    { leaf: true, text: 'Grouped Grid' },
                    { leaf: true, text: 'Locked Grid' },
                    { leaf: true, text: 'Grouped Header Grid' }
                ]
            },
            {
                text: 'Trees',
                expanded: true,
                children: [
                    { leaf: true, text: 'Basic Tree' }
                ]
            },
            {
                text: 'Tabs',
                expanded: true,
                children: [
                    { leaf: true, text: 'Basic Tabs' },
                    { leaf: true, text: 'Framed Tabs' },
                    { leaf: true, text: 'Icon Tabs' },
                    { leaf: true, text: 'Titled Tab Panels' }
                ]
            },
            {
                text: 'Windows',
                expanded: true,
                children: [
                    { leaf: true, text: 'Basic Window' }
                ]
            },
            {
                text: 'Toolbars',
                expanded: true,
                children: [
                    { leaf: true, text: 'Basic Toolbar' },
                    { leaf: true, text: 'Docked Toolbar' }
                ]
            }*/
        ]
    }
});
