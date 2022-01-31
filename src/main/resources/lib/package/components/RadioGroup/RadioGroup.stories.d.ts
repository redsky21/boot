import React from 'react';
declare const _default: {
    title: string;
    component: React.ForwardRefExoticComponent<{
        labelAlign?: "left" | "right" | undefined;
        labelWidth?: string | number | undefined;
        label?: string | undefined;
        required?: boolean | undefined;
        error?: boolean | undefined;
        WrapperProps?: Record<string, any> | undefined;
        children?: React.ReactNode;
    } & import("./RadioGroup.types").TRadioGroupContext & Omit<React.HTMLAttributes<HTMLDivElement>, "children" | "WrapperProps" | "label" | "error" | "required" | "labelAlign" | "labelWidth" | keyof import("./RadioGroup.types").TRadioGroupContext> & React.RefAttributes<HTMLDivElement>>;
    parameters: {
        props: {
            propTablesInclude: (React.ForwardRefExoticComponent<{
                labelAlign?: "left" | "right" | undefined;
                labelWidth?: string | number | undefined;
                label?: string | undefined;
                required?: boolean | undefined;
                error?: boolean | undefined;
                WrapperProps?: Record<string, any> | undefined;
                children?: React.ReactNode;
            } & import("./RadioGroup.types").TRadioGroupContext & Omit<React.HTMLAttributes<HTMLDivElement>, "children" | "WrapperProps" | "label" | "error" | "required" | "labelAlign" | "labelWidth" | keyof import("./RadioGroup.types").TRadioGroupContext> & React.RefAttributes<HTMLDivElement>> | React.ForwardRefExoticComponent<import("./RadioGroup.types").TRadioGroupItemProps & React.RefAttributes<HTMLDivElement>>)[];
        };
    };
};
export default _default;
export declare const ByDirectionView: {
    (): import("@emotion/react/jsx-runtime").JSX.Element;
    story: {
        name: string;
    };
};
