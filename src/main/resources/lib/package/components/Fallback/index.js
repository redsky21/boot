var _interopRequireDefault = require("@babel/runtime/helpers/interopRequireDefault");

Object.defineProperty(exports, "__esModule", {
  value: true
});
Object.defineProperty(exports, "ErrorFallback", {
  enumerable: true,
  get: function get() {
    return _ErrorFallback["default"];
  }
});
Object.defineProperty(exports, "NoResultFallback", {
  enumerable: true,
  get: function get() {
    return _NoResultFallback["default"];
  }
});
Object.defineProperty(exports, "PendingFallback", {
  enumerable: true,
  get: function get() {
    return _PendingFallback["default"];
  }
});
Object.defineProperty(exports, "default", {
  enumerable: true,
  get: function get() {
    return _Fallback["default"];
  }
});

var _Fallback = _interopRequireDefault(require("./Fallback"));

var _ErrorFallback = _interopRequireDefault(require("./ErrorFallback"));

var _PendingFallback = _interopRequireDefault(require("./PendingFallback"));

var _NoResultFallback = _interopRequireDefault(require("./NoResultFallback"));