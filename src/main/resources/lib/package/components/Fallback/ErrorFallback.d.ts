import React from 'react';
export declare type TErrorFallbackProps = {
    children?: React.ReactNode;
    size?: 'small' | 'medium';
} & React.HTMLAttributes<HTMLDivElement>;
export default function ErrorFallback({ children, size, ...rest }: TErrorFallbackProps): import("@emotion/react/jsx-runtime").JSX.Element;
