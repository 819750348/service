export default {
    block:['java','class','block','method','define'],
    javaBlock:{
        java:{},
        class:{},
        block:{},
    },
    key: {
        package: {inveriment:['java']},
        import: {inveriment:['java']},

        private: {depends:['method','paramter','class','interface','enum'],inveriment:['class'],unless:['block']},
        protected: {depends:['method','paramter','class','interface','enum'],inveriment:['class'],unless:['block']},
        public: {depends:['method','paramter','class','interface','enum'],inveriment:['java','class'],unless:['block']},

        class: {inveriment:['java','class','block','method']},
        interface: {inveriment:['java','class','block','method']},
        enum: {inveriment:['java','class','block','method']},

        abstract: {depends:['class','method']},
        final: {depends:['class','method','parameter']},
        static: {depends:['method','block','parameter']},
        native: {depends:['method']},

        const: {depends:['parameter']},
        transient: {depends:['parameter']},
        volatile: {depends:['parameter']},
        synchronized: {depends:'block',},
        strictfp: {show:false,depends:['interface','class','method','parameter']},

        implements: {relatives:[{from:'class',to:'interface'}]},
        extends: {relatives:[{from:'class',to:'class'},{from:'interface',to:'interface'}]},

        new: {operate:['init method']},
        this: {found:['parameter','method']},
        super: {found:['parameter','method']},

        void: {depends:['method']},
        boolean: {depends:['method','parameter']},
        byte: {depends:['method','parameter']},
        short: {depends:['method','parameter']},
        char: {depends:['method','parameter']},
        int: {depends:['method','parameter']},
        float: {depends:['method','parameter']},
        long: {depends:['method','parameter']},
        double: {depends:['method','parameter']},

        instanceof: {operate:['object']},
        assert: {operate:['boolean']},
        throws: {operate:['ExceptionClass']},
        return: {operate:['parameter']},

        try: {depends:['block']},
        catch: {depends:['block']},
        finally: {depends:['block']},
        throw: {operate:['exception']},

        do: {depends:['block']},
        while: {depends:['block']},
        for: {depends:['block']},
        continue: {inveriment:['cycle']},
        break: {inveriment:['cycle']},

        switch: {depends:['block']},
        case: {depends:['block']},
        default: {depends:['block']},

        if: {depends:['block']},
        else: {depends:['block']},

        goto: {show:false}
    },
    keyWord: {
        type: [{
            code: "class",
            begin: true,
            show: true
        }, {
            code: "enum",
            begin: true,
            show: true
        }, {
            code: "interface",
            begin: true,
            show: true
        }],
        parameter: ["void", "boolean", "byte", "short", "char", "int", "float", "long", "double", "string"],
        import: ["import", "package"],
        permission: ["private", "protected", "public"],
        block: ["synchronized", "try", "catch", "finally", "if", "else", "for", "do", "while", "switch", "case", "goto"],
        method: ["break", "assert", "throw", "throws", "return", "continue", "instanceof"],
        classRelative: ["extends", "implements"],
        objectReative: ["super", "this"],
        classDescript: ["final", "abstract"],
        parameterDescript: ["final", "const", "static"],
        methodDescript: ["final", "static", "native"],
        blockDescript: ["default"],
        descript: ["default", "const", "final", "static", "native", "transient", "strictfp"],
        new: ['Object'],
        import: ['class', 'interface', 'enum', 'static method']
    },
    formData() {
        var formatData = {};
        keyWord.forEach((k) => {
            formatData[k] = [];
            keyWord[k].forEach(item => {
                if (typeof item === 'string') {
                    formatData[k].push({
                        code: item,
                        show: false
                    });
                } else {
                    formatData[k].push(item);
                }
            })
        });
        return formatData;
    }
};
