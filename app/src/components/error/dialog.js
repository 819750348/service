import Vue from 'vue';
import dialogVue from './dialog.vue';
import merge from 'element-ui/src/utils/merge';
import { isVNode } from 'element-ui/src/utils/vdom';

const DialogConstructor = Vue.extend(dialogVue);
let instance;
let messageQueue = [], currentMessage;
const initInstance = () => {
    instance = new DialogConstructor({
        el: document.createElement('div')
    });
};
const showNextDialog = () => {
    if (!instance) {
        initInstance();
    }
    if (!instance.visible || instance.closeTimer) {
        if (messageQueue.length > 0) {
            currentMessage = messageQueue.shift();
            let options = currentMessage.options;
            for (let prop in options) {
                if (instance.hasOwnProperty(prop)) {
                    instance[prop] = options[prop];
                }
            }
            document.body.appendChild(instance.$el);
            Vue.nextTick(() => {
                instance.visible = true;
            });
        }
    }
}
const ErrorDialog = function (options) {
    if (Vue.prototype.$isServer) return;
    if (typeof options === 'string' || isVNode(options)) {
        options = {
            message: options
        };
        if (typeof arguments[1] === 'string') {
            options.details = arguments[1];
        }
    }
    messageQueue.push({
        options
    });
    showNextDialog();
}
ErrorDialog.alert = (message, details, options) => {
    if (typeof message === 'object') {
        options = message;
        message = '';
    } else if (details == undefined) {
        details = '';
    }
    return ErrorDialog(merge({
        message: message,
        details: details
    }, options));
};

export default ErrorDialog;
export { ErrorDialog };