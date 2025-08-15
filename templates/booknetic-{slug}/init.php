<?php

/*
 * Plugin Name: {AddonTitle}
 * Description: {AddonDescription}.
 * Version: 1.0.0
 * Author: FS Code
 * Author URI: https://www.booknetic.com
 * License: Commercial
 * Text Domain: booknetic-{slug}
 */

defined('ABSPATH') or exit;

require_once __DIR__ . '/vendor-{slug}/autoload.php';

add_filter('bkntc_addons_load', static function ($addons) {
    $addons[ \BookneticAddon\{SlugPascalCase}\{SlugPascalCase}Addon::getAddonSlug() ] = new \BookneticAddon\{SlugPascalCase}\{SlugPascalCase}Addon();

    return $addons;
});
