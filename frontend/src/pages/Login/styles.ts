import { ErrorMessage, Field } from 'formik';
import { Button } from 'react-bootstrap';
import styled from 'styled-components';

export const Container = styled.body`
    
`;

export const FieldLogin = styled(Field)`
    border: none;
    background-color: rgba(0, 0, 0, .25);
    color: #fdfdfd;
    margin-bottom: 1rem;
    padding: 1rem;
    font-size: 1.2rem;
    border-radius: 7px;
    width: 100%;
    transition: all .1s linear;

    &:hover {
        box-shadow: 0 4px 4px rgba(0, 0, 0, .5);
        outline: none;
    }
`;

export const ErrorLogin = styled(ErrorMessage)`
    color: #ff8080;
`;

export const ButtonLogin = styled(Button)`
    background: #00cc99;
    color: #fdfdfd;
    padding: .5rem 2rem;
    border: none;
    border-radius: 5px;
    margin-top: 2rem;

    &:hover {
        cursor: pointer;
    }
`;