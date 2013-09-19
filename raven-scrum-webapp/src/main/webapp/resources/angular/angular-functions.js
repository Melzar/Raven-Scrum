function emptyValidation(value)
{
  if(!value || value == "")
  {
    return false;
  } 
  return true;
}

function minLengthValidation(value)
{
   if(value.length < 4)
   {
      return false;
   }
   return true;
}