import React from 'react';
export declare type TNoResultFallbackProps = {
    size?: 'small' | 'medium';
    children?: React.ReactNode;
} & React.HTMLAttributes<HTMLDivElement>;
export default function NoResultFallback({ children, size, ...rest }: TNoResultFallbackProps): import("@emotion/react/jsx-runtime").JSX.Element;
export declare const Information: import("@emotion/styled").StyledComponent<{
    theme?: import("@emotion/react").Theme | undefined;
    as?: React.ElementType<any> | undefined;
}, React.DetailedHTMLProps<React.HTMLAttributes<HTMLDivElement>, HTMLDivElement>, {}>;
