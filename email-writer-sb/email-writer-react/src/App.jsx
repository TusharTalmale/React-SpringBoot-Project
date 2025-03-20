import { useState, useRef } from 'react';
import config from "./config";

import './App.css'
import { Box, Button, CircularProgress, Container, FormControl,  InputLabel, MenuItem, Select, TextField, Typography } from '@mui/material';
import axios from 'axios';

function App() {
  
const [emailContent ,setEmailContent] = useState('');
const [tone ,setTone] = useState('');
const [generateReply ,setGenerateReply] = useState('');
const [loading ,setLoading] = useState('');
const [error, setError] = useState('');
const [copied, setCopied] = useState(false);
const replyRef = useRef(null);


const handleSubmit = async () => {
  setLoading(true);
  setError('');
  try{

const response = await axios.post(`${config.backendUrl}/api/email/generate` , 
  { emailContent , tone}
);
    setGenerateReply(typeof response.data === 'string' ? response.data : JSON.stringify(response.data));
     // Auto-scroll to generated reply
     setTimeout(() => {
      if (replyRef.current) {
        replyRef.current.scrollIntoView({ behavior: 'smooth' });
      }
    }, 300);
  }
  catch (error){
    setError('Failed To Generate Email Reply. Try again 🔄 ');
    console.error(error);
  }
  finally{
    setLoading(false);
  }
}

const handleCopy = () => {
  navigator.clipboard.writeText(generateReply);
  setCopied(true);
  setTimeout(() => setCopied(false), 2000); // Reset after 2 seconds
};
  return (
    <Container maxWidth="md" sx = {{py : 4}}>
     <Typography variant='h3' component="h1" gutterBottom>
      Email Reply Generator 
     </Typography>

      <Box sx = {{mx:3}}>
        <TextField 
        fullWidth multiline 
        rows={6} variant='outlined' 
        label="Original Email Content"
        value={emailContent || ''}
        onChange={(e) => setEmailContent(e.target.value)}
        sx = {{mb:2}} />

        <FormControl fullWidth sx={{mb:2}}>
          <InputLabel>  Tone (Optional)</InputLabel>

          <Select
          value={tone || ''} label = {"Tone Optional"}
          onChange={(e) => setTone(e.target.value)}>
            <MenuItem value="">             None        </MenuItem>
            <MenuItem value="professional">Professional </MenuItem>
            <MenuItem value="casual">     Casual        </MenuItem>
            <MenuItem value="friendly">   Friendly      </MenuItem>
          </Select>

        </FormControl>

        <Button
          variant='contained'
          onClick={handleSubmit}
          disabled = {!emailContent || loading}
          fullWidth>
          {loading ? <CircularProgress size={24}/> : "Generate Reply 🚀"}
        </Button>    
      </Box>

      {error && ( 
        <Typography color='error' sx={{mb : 2}}>{error}</Typography>
      )}

      {generateReply && (
        <Box sx={{mt:3}}>
          <Typography variant='h6' gutterBottom> Generate Reply : </Typography>
          <TextField
          fullWidth
          multiline
          rows={6}
          variant='outlined'
          value={generateReply || ''}
          disabled={true}
          />
          <Button
          variant='outlined'
          sx={{ mt:2}}
          onClick={handleCopy}>    
          {copied ? "✅ Copied!" : "COPY TO CLIPBOARD 📋"} 
          </Button>
        </Box>

      )}
    </Container>
  );
}


export default App;
