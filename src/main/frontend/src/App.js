import React, {useState, useEffect, useCallback} from "react";
import logo from './logo.svg';
import './App.css';
//axios allows us to preform http requests
import axios from "axios";
import {useDropzone} from 'react-dropzone'

const UserProfiles = () => {
//useState returns a stateful value, and a function to update it.
  const[userProfiles, setUserProfiles] = useState([]);

  const fetchUserProfiles = () => {
    axios.get("http://localhost:8080/api/v1/user-profile").then(response =>{
      console.log(response);
      setUserProfiles(response.data);
    });
  };
  //the same as using componentDidMount()
  //if anything changes inside of the array, useEffect will be triggered again
  useEffect(() => {
    fetchUserProfiles();
  }, []);

  return userProfiles.map((userProfile, index ) => {
    return (
    <div key={index}>
      {userProfile.userProfileId ? <img 
      src={`http://localhost:8080/api/v1/user-profile/${userProfile.userProfileId}/image/download`}
      /> : null}
      <br/>
      <br/>
      <h1>{userProfile.username}</h1>
      <p>{userProfile.userProfileId}</p>
      <DropZone userProfileId={userProfile.userProfileId}/>
      <br/>
    </div>
    );
  });
};


  function DropZone({ userProfileId }) {
    const onDrop = useCallback(acceptedFiles => {
      //grabbing the first file
      const file = acceptedFiles[0];
      console.log(file);
      const formData = new FormData();
      //"file" is the same exact as the requestparam inside of our controller in springboot
      formData.append("file", file);
      
      axios.post(
        `http://localhost:8080/api/v1/user-profile/${userProfileId}/image/upload`,
      formData,
      {
        headers: {
          "Content-Type": "multipart/form-data"
        }
      }
      ).then(() => {
        console.log("file upload successfully")
      }).catch(err => {
        console.log(err);
      });
    }, [])
    const {getRootProps, getInputProps, isDragActive} = useDropzone({onDrop})
  
    return (
      <div {...getRootProps()}>
        <input {...getInputProps()} />
        {
          isDragActive ?
            <p>Drop the image here ...</p> :
            <p>Drag 'n' drop profile image, or click to select profile image</p>
        }
      </div>
    )
  }



function App() {
  return (
    <div className="App">
      <UserProfiles />
    </div>
  );
}

export default App;
