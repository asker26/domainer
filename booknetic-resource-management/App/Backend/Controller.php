<?php

namespace BookneticAddon\ResourceManagement\Backend;

use BookneticApp\Providers\Core\Capabilities;

use function \BookneticAddon\ResourceManagement\bkntc__;

class Controller extends \BookneticApp\Providers\Core\Controller
{

    public function index()
    {
    	Capabilities::must( 'resource-management' );
    }

}
