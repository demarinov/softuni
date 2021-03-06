"use strict";

var express = require('express');

var router = express.Router();

var _require = require('../utils'),
    auth = _require.auth;

var _require2 = require('../controllers'),
    themeController = _require2.themeController,
    postController = _require2.postController,
    artworkController = _require2.artworkController; // middleware that is specific to this router


router.get('/', artworkController.getArtworks);
router.post('/', auth(), artworkController.createArt);
router.get('/:artId', artworkController.getArt);
module.exports = router;
//# sourceMappingURL=artworks.dev.js.map
