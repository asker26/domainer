<?php

namespace BookneticAddon\ResourceManagement;

use BookneticApp\Providers\Core\AddonLoader;
use BookneticApp\Providers\Core\Capabilities;

function bkntc__ ( $text, $params = [], $esc = true )
{
	return \bkntc__( $text, $params, $esc, ResourceManagementAddon::getAddonTextDomain() );
}

class ResourceManagementAddon extends AddonLoader
{
	public function init ()
	{
		Capabilities::registerTenantCapability( 'resource-management', bkntc__('Resource Management') );

		if( ! Capabilities::tenantCan( 'resource-management' ) )
			return;

		Capabilities::register( 'resource-management', bkntc__('Resource Management') );
	}

	public function initBackend ()
	{
		if( ! Capabilities::tenantCan( 'resource-management' ) )
			return;

        if( ! Capabilities::userCan( 'resource-management' ) ) {
            return;
        }

        //your code here
    }
}