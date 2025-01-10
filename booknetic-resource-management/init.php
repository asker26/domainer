<?php
/*
 * Plugin Name: Resource Management
 * Description: Manage your resources, staff, and equipment efficiently..
 * Version: 1.0.0
 * Author: FS Code
 * Author URI: https://www.booknetic.com
 * License: Commercial
 * Text Domain: booknetic-resource-management
 */

defined( 'ABSPATH' ) or exit;

require_once __DIR__ . '/vendor/autoload.php';

add_filter('bkntc_addons_load', function ($addons)
{
    $addons[ \BookneticAddon\ResourceManagement\ResourceManagementAddon::getAddonSlug() ] = new \BookneticAddon\ResourceManagement\ResourceManagementAddon();

    return $addons;
});
