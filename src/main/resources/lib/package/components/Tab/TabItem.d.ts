import React from 'react';
declare type Props = {
    label: string;
    value: string;
    disabled?: boolean;
};
export declare type TTabItemProps = React.PropsWithChildren<Props & Omit<React.HTMLAttributes<any>, keyof Props>>;
export default function TabItem({ label, value, disabled, children, ...rest }: TTabItemProps): import("@emotion/react/jsx-runtime").JSX.Element | null;
export {};
