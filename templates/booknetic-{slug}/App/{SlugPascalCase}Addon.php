<?php

namespace BookneticAddon\{SlugPascalCase};

use BookneticApp\Providers\Core\AddonLoader;
use BookneticApp\Providers\Core\Capabilities;

function bkntc__ ( $text, $params = [], $esc = true )
{
	return \bkntc__( $text, $params, $esc, {SlugPascalCase}Addon::getAddonTextDomain() );
}

class {SlugPascalCase}Addon extends AddonLoader
{
	public function init ()
	{
		Capabilities::registerTenantCapability( '{slug}', bkntc__('{AddonTitle}') );

		if( ! Capabilities::tenantCan( '{slug}' ) )
			return;

		Capabilities::register( '{slug}', bkntc__('{AddonTitle}') );
	}

	public function initBackend ()
	{
		if( ! Capabilities::tenantCan( '{slug}' ) )
			return;

        if( ! Capabilities::userCan( '{slug}' ) ) {
            return;
        }

        //your code here
    }
}