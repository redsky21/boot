import React from 'react';
declare type Props = {
    value?: string;
    initialValue?: string;
    onChange?: (val: string) => void;
    hideDivider?: boolean;
    gap?: number | string;
    WrapperProps?: Record<string, any>;
};
export declare type TTabProps = React.PropsWithChildren<Props & Omit<React.HTMLAttributes<any>, keyof Props>>;
export default function Tab({ initialValue, hideDivider, gap, children, WrapperProps, ...rest }: TTabProps): import("@emotion/react/jsx-runtime").JSX.Element;
export {};
