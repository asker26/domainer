<?php

namespace BookneticAddon\{SlugPascalCase}\Backend;

use BookneticApp\Providers\Core\Capabilities;

use function \BookneticAddon\{SlugPascalCase}\bkntc__;

class Controller extends \BookneticApp\Providers\Core\Controller
{

    public function index()
    {
    	Capabilities::must( '{slug}' );
    }

}
