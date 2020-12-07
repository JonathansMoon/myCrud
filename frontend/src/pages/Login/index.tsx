import axios from 'axios';
import { Formik, ErrorMessage, Form , Field} from 'formik';
import React from 'react';
import { Redirect } from 'react-router-dom';
import * as yup from 'yup';

import { Container, ErrorLogin, FieldLogin, ButtonLogin } from './styles';

const Login: React.FC = () => {


  const handleSubmit = async values => {

    axios.post('http://localhost:8080/login', values)
      .then(resp => {
        console.log(resp);
        
        const { data } = resp
        if (data) {
          localStorage.setItem('app-token', data);
          <Redirect to="/" />
        }
      });
  };

  const validations = yup.object().shape({
    username: yup.string().required(),
    password: yup.string().min(6).required()
  });
  
  return (
    <Container> 
      <Formik initialValues={{}} onSubmit={handleSubmit} validationSchema={validations}>
        <Form>
            <FieldLogin name="username" placeholder="Enter username" className="LoginField" />
            <ErrorLogin component="span" name="username" className="LoginError" />

            <FieldLogin type="password" name="password" placeholder="Password" className="LoginField" />
            <ErrorLogin component="span" name="password" className="LoginError" />

          <ButtonLogin variant="primary" type="submit">
            Submit
          </ButtonLogin>
        </Form>
      </Formik>
    </Container>
  );
}

export default Login;